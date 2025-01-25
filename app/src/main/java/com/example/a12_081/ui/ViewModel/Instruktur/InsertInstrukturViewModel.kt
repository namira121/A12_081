package com.example.a12_081.ui.ViewModel.Instruktur

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12_081.model.instruktur
import com.example.a12_081.repository.InstrukturRepository
import kotlinx.coroutines.launch

data class InsertInstrukturUiEvent(
    val id_instruktur: String= "",
    val nama_instruktur: String= "",
    val email: String= "",
    val nomor_telepon: String= "",
    val deskripsi: String= "",
)

data class InsertInstrukturUiState(
    val insertInstrukturUiEvent: InsertInstrukturUiEvent = InsertInstrukturUiEvent()
)

fun InsertInstrukturUiEvent.toIns(): instruktur = instruktur(
    id_instruktur = id_instruktur,
    nama_instruktur=nama_instruktur,
    email=email,
    nomor_telepon=nomor_telepon,
    deskripsi=deskripsi
)

fun instruktur.toInsertInstrukturUiEvent():InsertInstrukturUiEvent = InsertInstrukturUiEvent(
    id_instruktur = id_instruktur,
    nama_instruktur=nama_instruktur,
    email=email,
    nomor_telepon=nomor_telepon,
    deskripsi=deskripsi
)

fun instruktur.toUiStateIns(): InsertInstrukturUiState = InsertInstrukturUiState(
    insertInstrukturUiEvent = toInsertInstrukturUiEvent()
)

class InsertInstrukturViewModel (private val ins: InstrukturRepository): ViewModel(){
    var InsUiState by mutableStateOf(InsertInstrukturUiState())
        private set

    fun updateInsertInsState(insertInstrukturUiEvent: InsertInstrukturUiEvent){
        InsUiState = InsertInstrukturUiState(insertInstrukturUiEvent= insertInstrukturUiEvent)
    }

    suspend fun insertIns(){
        viewModelScope.launch {
            try {
                ins.insertInstruktur(InsUiState.insertInstrukturUiEvent.toIns())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}