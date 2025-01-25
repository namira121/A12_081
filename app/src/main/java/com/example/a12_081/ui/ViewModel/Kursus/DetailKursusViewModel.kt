package com.example.a12_081.ui.ViewModel.Kursus

data class DetailKursusViewModel(
    val detailKursusUiEvent: InsertKursusUiEvent = InsertKursusUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailKursusUiEvent == InsertKursusUiEvent()
    val isUiEventNotEmpty: Boolean
        get() = detailKursusUiEvent != InsertKursusUiEvent()
}
