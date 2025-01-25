package com.example.a12_081.ui.ViewModel.Kursus

data class InsertKursusUiEvent(
    val id_kursus: String = "",
    val nama_kursus: String = "",
    val deskripsi: String ="",
    val kategori: String="",
    val harga: String="",
    val id_instruktur: String=""
)