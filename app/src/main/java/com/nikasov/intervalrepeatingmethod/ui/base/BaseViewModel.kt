package com.nikasov.intervalrepeatingmethod.ui.base

import androidx.lifecycle.ViewModel
import com.nikasov.intervalrepeatingmethod.common.util.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

open class BaseViewModel: ViewModel() {
    protected val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState : StateFlow<UiState> = _uiState

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext: CoroutineContext, throwable: Throwable ->

    }
}