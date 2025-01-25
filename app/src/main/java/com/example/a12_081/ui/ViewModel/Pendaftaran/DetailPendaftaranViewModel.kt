package com.example.a12_081.ui.ViewModel.Pendaftaran

import com.example.a12_081.model.pendaftaran

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

fun pendaftaran.toDetailDaftarUiEvent(): InsertDaftarUiEvent{
    return InsertDaftarUiEvent(
        id_pendaftaran =id_pendaftaran,
        id_siswa=id_siswa,
        id_kursus=id_kursus,
        tanggal_pendaftaran=tanggal_pendaftaran
    )
}