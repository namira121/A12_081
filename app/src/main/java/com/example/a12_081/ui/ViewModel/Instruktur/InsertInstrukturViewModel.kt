package com.example.a12_081.ui.ViewModel.Instruktur

data class InsertInstrukturEvent(
    val id_instruktur: String= "",
    val nama_instruktur: String= "",
    val email: String= "",
    val nomor_telepon: String= "",
    val deskripsi: String= "",
)

data class InsertInstrukturUiState(
    val insertInstrukturUiEvent: InsertInstrukturEvent = InsertInstrukturEvent()
)