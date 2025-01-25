package com.example.a12_081.ui.ViewModel.Pendaftaran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12_081.model.pendaftaran
import com.example.a12_081.repository.PendaftaranRepository
import kotlinx.coroutines.launch
import java.lang.Exception

data class InsertDaftarUiState(
    val insertDaftarUiEvent: InsertDaftarUiEvent = InsertDaftarUiEvent()
)

data class InsertDaftarUiEvent(
    val id_pendaftaran:String = "",
    val id_siswa: String = "",
    val id_kursus: String = "",
    val tanggal_pendaftaran: String = ""
)

fun InsertDaftarUiEvent.toDtr(): pendaftaran = pendaftaran(
    id_pendaftaran =id_pendaftaran,
    id_siswa=id_siswa,
    id_kursus=id_kursus,
    tanggal_pendaftaran=tanggal_pendaftaran
)

fun pendaftaran.toUiStateDtr(): InsertDaftarUiState = InsertDaftarUiState(
    insertDaftarUiEvent = toInsertDaftarUiEvent()
)

fun pendaftaran.toInsertDaftarUiEvent():InsertDaftarUiEvent = InsertDaftarUiEvent(
    id_pendaftaran =id_pendaftaran,
    id_siswa=id_siswa,
    id_kursus=id_kursus,
    tanggal_pendaftaran=tanggal_pendaftaran
)

class InsertPendaftaranViewModel(private val dtr: PendaftaranRepository): ViewModel(){
    var dtrUiState by mutableStateOf(InsertDaftarUiState())

    fun updateInsertDaftarState(insertDaftarUiEvent: InsertDaftarUiEvent){
        dtrUiState = InsertDaftarUiState(insertDaftarUiEvent = insertDaftarUiEvent)
    }

    suspend fun insertDtr(){
        viewModelScope.launch {
            try {
                dtr.insertPendaftaran(dtrUiState.insertDaftarUiEvent.toDtr())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

