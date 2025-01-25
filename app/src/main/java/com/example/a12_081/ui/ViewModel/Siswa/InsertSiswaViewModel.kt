package com.example.a12_081.ui.ViewModel.Siswa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12_081.model.siswa
import com.example.a12_081.repository.SiswaRepository
import kotlinx.coroutines.launch
import java.lang.Exception

data class InsertSiswaUiState(
    val insertSiswaUiEvent: InsertSiswaUiEvent = InsertSiswaUiEvent()
)

data class InsertSiswaUiEvent(
    val id_siswa:String = "",
    val nama_siswa: String = "",
    val email: String = "",
    val nomor_telepon: String = ""
)

fun InsertSiswaUiEvent.toSw(): siswa = siswa(
    id_siswa =id_siswa,
    nama_siswa=nama_siswa,
    email=email,
    nomor_telepon=nomor_telepon
)

fun siswa.toUiStateSw(): InsertSiswaUiState = InsertSiswaUiState(
    insertSiswaUiEvent = toInsertSiswaUiEvent()
)

fun siswa.toInsertSiswaUiEvent():InsertSiswaUiEvent = InsertSiswaUiEvent(
    id_siswa =id_siswa,
    nama_siswa=nama_siswa,
    email=email,
    nomor_telepon=nomor_telepon
)

class InsertSiswaViewModel(private val sw: SiswaRepository): ViewModel(){
    var swUiState by mutableStateOf(InsertSiswaUiState())

    fun updateInsertSiswaState(insertSiswaUiEvent: InsertSiswaUiEvent){
        swUiState = InsertSiswaUiState(insertSiswaUiEvent = insertSiswaUiEvent)
    }

    suspend fun insertSw(){
        viewModelScope.launch {
            try {
                sw.insertSiswa(swUiState.insertSiswaUiEvent.toSw())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}