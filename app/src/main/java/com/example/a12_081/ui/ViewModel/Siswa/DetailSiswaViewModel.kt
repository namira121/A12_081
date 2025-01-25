package com.example.a12_081.ui.ViewModel.Siswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12_081.model.siswa
import com.example.a12_081.repository.SiswaRepository
import com.example.a12_081.ui.View.Siswa.DestinasiDetailSiswa
import kotlinx.coroutines.launch

data class DetailSiswaUiState(
    val detailSiswaUiEvent: InsertSiswaUiEvent = InsertSiswaUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String=""
){
    val isUiEventEmpty: Boolean
        get() = detailSiswaUiEvent== InsertSiswaUiEvent()

    val isUiEventNoEmpty: Boolean
        get() = detailSiswaUiEvent != InsertSiswaUiEvent()
}

fun siswa.toDetailSiswaUiEvent(): InsertSiswaUiEvent{
    return InsertSiswaUiEvent(
        id_siswa = id_siswa,
        nama_siswa = nama_siswa,
        email = email,
        nomor_telepon = nomor_telepon
    )
}

class DetailSiswaViewModel (
    savedStateHandle: SavedStateHandle,
    private val siswaRepository: SiswaRepository
): ViewModel(){
    private val id_siswa: String = checkNotNull(savedStateHandle[DestinasiDetailSiswa.ID_SISWA])

    var detailSiswaUiState: DetailSiswaUiState by mutableStateOf(DetailSiswaUiState())
        private set

    init {
        getSiswaByID()
    }

    private fun getSiswaByID(){
        viewModelScope.launch {
            detailSiswaUiState = DetailSiswaUiState(isLoading = true)
            try {
                val result = siswaRepository.getSiswaByID(id_siswa)
                detailSiswaUiState = DetailSiswaUiState(
                    detailSiswaUiEvent = result.toDetailSiswaUiEvent(),
                    isLoading = false
                )
            }catch ( e: Exception){
                detailSiswaUiState = DetailSiswaUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown"
                )
            }
        }
    }

    fun deleteSw() {
        viewModelScope.launch {
            detailSiswaUiState = DetailSiswaUiState(isLoading = true)
            try {
                siswaRepository.deleteSiswa(id_siswa)

                detailSiswaUiState = DetailSiswaUiState(isLoading = false)
            } catch (e: Exception) {
                detailSiswaUiState = DetailSiswaUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }
}