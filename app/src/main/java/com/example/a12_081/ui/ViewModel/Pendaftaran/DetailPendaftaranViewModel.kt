package com.example.a12_081.ui.ViewModel.Pendaftaran

data class DetailDaftarUiState(
    val detailDaftarUiState: InsertDaftarUiEvent = InsertDaftarUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errormessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailDaftarUiState == InsertDaftarUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailDaftarUiState != InsertDaftarUiEvent()
}