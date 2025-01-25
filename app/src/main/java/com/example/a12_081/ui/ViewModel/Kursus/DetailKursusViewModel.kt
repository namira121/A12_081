package com.example.a12_081.ui.ViewModel.Kursus

import com.example.a12_081.model.kursus

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

fun kursus.toDetailKursusUiEvent(): InsertKursusUiEvent{
    return InsertKursusUiEvent(
        id_kursus = id_kursus,
        nama_kursus = nama_kursus,
        deskripsi = deskripsi,
        kategori = kategori,
        harga = harga,
        id_instruktur= id_instruktur
    )
}
