package com.example.a12_081.ui.ViewModel.Instruktur

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12_081.model.instruktur
import com.example.a12_081.repository.InstrukturRepository
import kotlinx.coroutines.launch

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

class DetailInstrukturViewModel(
    savedStateHandle: SavedStateHandle,
    private val instrukturRepository: InstrukturRepository
): ViewModel() {
    private val id_instruktur: String = checkNotNull(savedStateHandle[DestinasiDetailInstruktur.ID_INSTRUKTUR])
    var detailInstrukturUiState: DetailInstrukturUiState by mutableStateOf(DetailInstrukturUiState())
        private set
    init {
        getInstukturbyID()
    }

    private fun getInstukturbyID(){
        viewModelScope.launch {
            detailInstrukturUiState = DetailInstrukturUiState(isLoading = true)
            try {
                val result = instrukturRepository.getInstrukturByID(id_instruktur)
                detailInstrukturUiState = DetailInstrukturUiState(
                    detailInstrukturUiEvent = result.toDetailInstrukturUiEvent(),
                    isLoading = false
                )
            }catch (e:Exception){
                detailInstrukturUiState = DetailInstrukturUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown"
                )
            }
        }
    }

    fun deleteIns(){
        viewModelScope.launch {
            detailInstrukturUiState= DetailInstrukturUiState(isLoading = true)
            try {
                instrukturRepository.deleteInstruktur(id_instruktur)
                detailInstrukturUiState = DetailInstrukturUiState(isLoading = false)
            }catch (e:Exception){
                detailInstrukturUiState = DetailInstrukturUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }
}