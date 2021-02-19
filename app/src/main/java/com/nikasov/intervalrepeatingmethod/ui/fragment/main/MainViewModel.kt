package com.nikasov.intervalrepeatingmethod.ui.fragment.main

import com.nikasov.intervalrepeatingmethod.repository.WordRepository
import com.nikasov.intervalrepeatingmethod.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : BaseViewModel() {

    val wordList = wordRepository.readAllWords()

}