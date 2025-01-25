package com.example.a12_081.ui.ViewModel.Instruktur

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.a12_081.repository.InstrukturRepository

class UpdateInstrukturViewModel(savedStateHandle: SavedStateHandle,private val instrukturRepository: InstrukturRepository): ViewModel(){
    var updateInstrukturUiState by mutableStateOf(InsertInstrukturUiState())
        private set

    private val _id_instruktur: String = checkNotNull(savedStateHandle[DestinasiUpdateInstruktur.ID_INSTRUKTUR])


}
