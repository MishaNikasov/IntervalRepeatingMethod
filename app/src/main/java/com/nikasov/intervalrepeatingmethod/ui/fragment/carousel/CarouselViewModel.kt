package com.nikasov.intervalrepeatingmethod.ui.fragment.carousel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikasov.intervalrepeatingmethod.data.domain.Word
import com.nikasov.intervalrepeatingmethod.repository.WordRepository
import com.nikasov.intervalrepeatingmethod.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CarouselViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : BaseViewModel() {

    private val currentDate = Calendar.getInstance()

    val uncompletedWords = MutableLiveData<List<Word>>()
    val isCompleted = MutableLiveData<Boolean>()

    fun getUncompletedWords() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = wordRepository.readAllUncompletedWords(currentDate.timeInMillis)
            uncompletedWords.postValue(list)
        }
    }

    fun setResult(word: Word, isCompleted: Boolean) {
        if (isCompleted) {
            viewModelScope.launch(Dispatchers.IO) {
                wordRepository.wordCorrect(word.id ?: -1, calculateRepeatDate(word.repeatCount))
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                wordRepository.wordFailed(word.id ?: -1, calculateRepeatDate(0))
            }
        }
    }

    private fun calculateRepeatDate(repeatCount: Int): Long {
        return when (repeatCount) {
            0 -> {
                currentDate.add(Calendar.DATE, 1)
                currentDate.timeInMillis
            }
            1 -> {
                currentDate.add(Calendar.DATE, 7)
                currentDate.timeInMillis
            }
            3 -> {
                currentDate.add(Calendar.DATE, 16)
                currentDate.timeInMillis
            }
            4 -> {
                currentDate.add(Calendar.DATE, 35)
                currentDate.timeInMillis
            }
            else -> currentDate.timeInMillis
        }
    }
}