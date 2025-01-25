package com.example.a12_081.ui.ViewModel.Kursus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12_081.model.kursus
import com.example.a12_081.repository.KursusRepository
import kotlinx.coroutines.launch

data class InsertKursusUiEvent(
    val id_kursus: String = "",
    val nama_kursus: String = "",
    val deskripsi: String ="",
    val kategori: String="",
    val harga: String="",
    val id_instruktur: String=""
)

data class InsertKursusUiState(
    val insertKursusUiEvent: InsertKursusUiEvent = InsertKursusUiEvent()
)

fun InsertKursusUiEvent.toKrs(): kursus = kursus(
    id_kursus = id_kursus,
    nama_kursus = nama_kursus,
    deskripsi = deskripsi,
    kategori = kategori,
    harga = harga,
    id_instruktur= id_instruktur
)

//mengkonversi model kursus mjd event untuk ditampulkan di Ui
fun kursus.toInsertKursusUiEvent(): InsertKursusUiEvent = InsertKursusUiEvent(
    id_kursus = id_kursus,
    nama_kursus = nama_kursus,
    deskripsi = deskripsi,
    kategori = kategori,
    harga = harga,
    id_instruktur= id_instruktur
)

fun kursus.toUiStateKrs(): InsertKursusUiState = InsertKursusUiState(
    insertKursusUiEvent = toInsertKursusUiEvent()
)

class InsertKursusViewModel (private val krs: KursusRepository): ViewModel(){
    var krsUiState by mutableStateOf(InsertKursusUiState())
        private set

    fun updateInsertKursusState(insertKursusUiEvent: InsertKursusUiEvent){
        krsUiState = InsertKursusUiState(insertKursusUiEvent = insertKursusUiEvent)
    }

    suspend fun insertKrs(){
        viewModelScope.launch {
            try {
                krs.insertKursus(krsUiState.insertKursusUiEvent.toKrs())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}