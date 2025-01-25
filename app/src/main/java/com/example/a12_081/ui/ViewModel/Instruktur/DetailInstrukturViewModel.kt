package com.example.a12_081.ui.ViewModel.Instruktur

import com.example.a12_081.model.instruktur

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

fun instruktur.toDetailInstrukturUiEvent(): InsertInstrukturUiEvent{
    return InsertInstrukturUiEvent(
        id_instruktur = id_instruktur,
        nama_instruktur=nama_instruktur,
        email=email,
        nomor_telepon=nomor_telepon,
        deskripsi=deskripsi
    )
}