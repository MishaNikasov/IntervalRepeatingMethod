package com.nikasov.intervalrepeatingmethod.common.util

sealed class UiState {
    data class Error(val exception: Exception): UiState()
    data class Loading(val inProgress: Boolean): UiState()
    object Successes: UiState()
    object Empty: UiState()
}