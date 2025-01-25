package com.example.a12_081.ui.ViewModel.Kursus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12_081.model.kursus
import com.example.a12_081.repository.KursusRepository
import kotlinx.coroutines.launch

data class DetailKursusUiState(
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

class DetailKursusViewModel(
    savedStateHandle: SavedStateHandle,
    private val kursusRepository: KursusRepository
): ViewModel(){
    private val id_kursus:String = checkNotNull(savedStateHandle[DestinasiDetailKursus.ID_KURSUS])
    var detailKursusUiState: DetailKursusUiState by mutableStateOf(DetailKursusUiState())
        private set

    init {
        getKursusByID()
    }

    private fun getKursusByID(){
        viewModelScope.launch {
            detailKursusUiState = DetailKursusUiState(isLoading = true)
            try {
                val result = kursusRepository.getKursusByID(id_kursus)
                detailKursusUiState = DetailKursusUiState(
                    detailKursusUiEvent = result.toDetailKursusUiEvent(),
                    isLoading = true
                )
            }catch (e:Exception){
                detailKursusUiState = DetailKursusUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown"
                )
            }
        }
    }

    fun deleteKrs(){
        viewModelScope.launch {
            detailKursusUiState = DetailKursusUiState(isLoading =  true)
            try {
                kursusRepository.deleteKursus(id_kursus)
                detailKursusUiState = DetailKursusUiState(isLoading = false)
            }catch (e:Exception){
                detailKursusUiState = DetailKursusUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message?: :Unknowd Error
                )
            }
        }
    }
}
