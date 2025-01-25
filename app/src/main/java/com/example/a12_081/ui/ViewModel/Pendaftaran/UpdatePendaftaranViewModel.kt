package com.example.a12_081.ui.ViewModel.Pendaftaran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12_081.repository.PendaftaranRepository
import kotlinx.coroutines.launch

class UpdateDaftarViewModel(savedStateHandle: SavedStateHandle, private val pendaftaranRepository: PendaftaranRepository): ViewModel(){
    var updateDaftarUiState by mutableStateOf(InsertDaftarUiState())
        private set

    private val _id_pendafataran: String = checkNotNull(savedStateHandle[DestinasiUpdateDaftar.ID_SISWA])

    init {
        viewModelScope.launch {
            updateDaftarUiState = pendaftaranRepository.getPendaftaranByID(_id_pendafataran)
                .toUiStateDtr()
        }
    }

    fun updateInsertDtrState(insertDaftarUiEvent: InsertDaftarUiEvent){
        updateDaftarUiState = InsertDaftarUiState(insertDaftarUiEvent = insertDaftarUiEvent)
    }

    fun updateDtr(){
        viewModelScope.launch {
            try {
                pendaftaranRepository.updatePendaftaran(_id_pendafataran,updateDaftarUiState.insertDaftarUiEvent.toDtr())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}