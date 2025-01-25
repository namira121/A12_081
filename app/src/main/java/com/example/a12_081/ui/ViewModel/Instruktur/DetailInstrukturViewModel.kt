package com.example.a12_081.ui.ViewModel.Instruktur

data class DetailInstrukturUiState(
    val detailInstrukturUiEvent: InsertInstrukturUiEvent = InsertInstrukturUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailInstrukturUiEvent == InsertInstrukturUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailInstrukturUiEvent != InsertInstrukturUiEvent()
}