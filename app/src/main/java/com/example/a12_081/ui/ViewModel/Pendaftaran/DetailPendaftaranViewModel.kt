package com.example.a12_081.ui.ViewModel.Pendaftaran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12_081.model.pendaftaran
import com.example.a12_081.repository.PendaftaranRepository
import kotlinx.coroutines.launch

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

class DetailPendaftaranViewModel(
    savedStateHandle: SavedStateHandle,
    private val pendaftaranRepository: PendaftaranRepository
): ViewModel(){
    private val id_pendaftaran: String = checkNotNull(savedStateHandle[DestinasiDetailDaftar.ID_PENDAFTARAN])

    var detailDaftarUiState: DetailDaftarUiState by mutableStateOf(DetailDaftarUiState())
        private set

    init {
        getPendaftaranByID()
    }

    private fun getPendaftaranByID(){
        viewModelScope.launch {
            detailDaftarUiState = DetailDaftarUiState(isLoading = true)
            try {
                val result = pendaftaranRepository.getPendaftaranByID(id_pendaftaran)
                detailDaftarUiState = DetailDaftarUiState(
                    detailDaftarUiState = result.toDetailDaftarUiEvent(),
                    isLoading = false
                )
            }catch (e: Exception){
                detailDaftarUiState = DetailDaftarUiState(
                    isLoading = false,
                    isError = true,
                    errormessage = e.message ?: "Unknown"
                )
            }
        }
    }
}