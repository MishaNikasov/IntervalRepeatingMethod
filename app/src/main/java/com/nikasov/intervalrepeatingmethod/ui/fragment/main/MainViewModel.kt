package com.nikasov.intervalrepeatingmethod.ui.fragment.main

import androidx.lifecycle.viewModelScope
import com.nikasov.intervalrepeatingmethod.repository.WordRepository
import com.nikasov.intervalrepeatingmethod.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : BaseViewModel() {

    val wordList = wordRepository.readAllWords()

    fun deleteWordById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            wordRepository.deleteWordById(id)
        }
    }

}