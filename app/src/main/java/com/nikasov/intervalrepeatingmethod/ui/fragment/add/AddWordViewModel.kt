package com.nikasov.intervalrepeatingmethod.ui.fragment.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nikasov.intervalrepeatingmethod.common.util.UiState
import com.nikasov.intervalrepeatingmethod.data.domain.Word
import com.nikasov.intervalrepeatingmethod.repository.WordRepository
import com.nikasov.intervalrepeatingmethod.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : BaseViewModel() {

    val rusWord = MutableLiveData<String>()
    val engWord = MutableLiveData<String>()

    fun insertWord() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading(true)
            val word = Word(
                eng = engWord.value ?: "",
                rus = rusWord.value ?: "",
                createDate = Calendar.getInstance()
            )
            wordRepository.insertWord(word)
            _uiState.value = UiState.Loading(false)
            _uiState.value = UiState.Successes
        }
    }
}