package com.example.a12_081.ui.ViewModel.Kursus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12_081.model.kursus
import com.example.a12_081.repository.KursusRepository
import com.example.a12_081.ui.View.Kursus.DestinasiUpdateKursus
import kotlinx.coroutines.launch

class UpdateKursusViewModel (savedStateHandle: SavedStateHandle, private val kursusRepository: KursusRepository): ViewModel(){

    var updateKursusUiState by mutableStateOf(InsertKursusUiState())
        private set

    private val _id_kursus: String = checkNotNull(savedStateHandle[DestinasiUpdateKursus.ID_KURSUS])

    init {
        viewModelScope.launch {
            updateKursusUiState = kursusRepository.getKursusByID(_id_kursus)
                .toUiStateKrs()
        }
    }

    fun updateInsertKrsState(insertKursusUiEvent: InsertKursusUiEvent){
        updateKursusUiState = InsertKursusUiState(insertKursusUiEvent= insertKursusUiEvent)
    }

    fun updateKrs(){
        viewModelScope.launch {
            try {
                kursusRepository.updateKursus(_id_kursus, updateKursusUiState.insertKursusUiEvent.toKrs())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}