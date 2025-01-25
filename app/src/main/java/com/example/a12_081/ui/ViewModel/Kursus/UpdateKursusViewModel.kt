package com.example.a12_081.ui.ViewModel.Kursus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.a12_081.model.kursus
import com.example.a12_081.repository.KursusRepository

class UpdateKursusViewModel (savedStateHandle: SavedStateHandle, private val kursusRepository: KursusRepository): ViewModel(){

    var updateKursusUiState by mutableStateOf(InsertKursusUiState())
        private set

    private val _id_kursus: String = checkNotNull(savedStateHandle[DestinasiUpdateKursus.ID_KURSUS])


}