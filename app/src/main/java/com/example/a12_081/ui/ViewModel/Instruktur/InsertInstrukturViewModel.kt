package com.example.a12_081.ui.ViewModel.Instruktur

import com.example.a12_081.model.instruktur

data class InsertInstrukturUiEvent(
    val id_instruktur: String= "",
    val nama_instruktur: String= "",
    val email: String= "",
    val nomor_telepon: String= "",
    val deskripsi: String= "",
)

data class InsertInstrukturUiState(
    val insertInstrukturUiEvent: InsertInstrukturUiEvent = InsertInstrukturUiEvent()
)

fun InsertInstrukturUiEvent.toIns(): instruktur = instruktur(
    id_instruktur = id_instruktur,
    nama_instruktur=nama_instruktur,
    email=email,
    nomor_telepon=nomor_telepon,
    deskripsi=deskripsi
)

fun instruktur.toInsertInstrukturUiEvent():InsertInstrukturUiEvent = InsertInstrukturUiEvent(
    id_instruktur = id_instruktur,
    nama_instruktur=nama_instruktur,
    email=email,
    nomor_telepon=nomor_telepon,
    deskripsi=deskripsi
)

fun instruktur.toUiStateIns(): InsertInstrukturUiState = InsertInstrukturUiState(
    insertInstrukturUiEvent = toInsertInstrukturUiEvent()
)