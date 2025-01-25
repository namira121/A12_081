package com.example.a12_081.ui.ViewModel.Siswa

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12_081.repository.SiswaRepository
import kotlinx.coroutines.launch

class UpdateSiswaViewModel(savedStateHandle: SavedStateHandle, private val siswaRepository: SiswaRepository): ViewModel(){
    var updateSiswaUiState by mutableStateOf(InsertSiswaUiState())
        private set

    private val _id_siswa: String = checkNotNull(savedStateHandle[DestinasiUpdateSiswa.ID_SISWA])

    init {
        viewModelScope.launch {
            updateSiswaUiState = siswaRepository.getSiswaByID(_id_siswa)
                .toUiStateSw()
        }
    }

    fun updateInsertSwState(insertSiswaUiEvent: InsertSiswaUiEvent){
        updateSiswaUiState = InsertSiswaUiState(insertSiswaUiEvent = insertSiswaUiEvent)
    }

    fun updateSw(){
        viewModelScope.launch {
            try {
                siswaRepository.updateSiswa(_id_siswa,updateSiswaUiState.insertSiswaUiEvent.toSw())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}