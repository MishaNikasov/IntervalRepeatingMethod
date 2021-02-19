package com.nikasov.intervalrepeatingmethod.ui.fragment.carousel

import com.nikasov.intervalrepeatingmethod.repository.WordRepository
import com.nikasov.intervalrepeatingmethod.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarouselViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : BaseViewModel() {

    val uncompletedWords = wordRepository.readAllWords()

}