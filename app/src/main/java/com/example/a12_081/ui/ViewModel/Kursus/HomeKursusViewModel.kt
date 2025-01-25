package com.example.a12_081.ui.ViewModel.Kursus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.a12_081.model.kursus
import com.example.a12_081.model.pendaftaran
import com.example.a12_081.model.siswa
import com.example.a12_081.repository.KursusRepository
import com.example.a12_081.repository.SiswaRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeKursusUiState{
    data class Success(val kursus: List<kursus>): HomeKursusUiState()
    object Error : HomeKursusUiState()
    object Loading : HomeKursusUiState()
}

class HomeKursusViewModel(private val krs: KursusRepository): ViewModel(){
    var krsUiState: HomeKursusUiState by mutableStateOf(HomeKursusUiState.Loading)
        private set

    init {
        getKursus()
    }

    fun getKursus(){
        viewModelScope.launch {
            krsUiState = HomeKursusUiState.Loading
            krsUiState = try {
                HomeKursusUiState.Success(krs.getKursus().data)
            }catch (e: IOException){
                HomeKursusUiState.Error
            }catch (e: HttpException){
                HomeKursusUiState.Error
            }
        }
    }

    fun deleteKursus(id_kursus: String){
        viewModelScope.launch {
            try {
                krs.deleteKursus(id_kursus)
            }catch (e: IOException){
                HomeKursusUiState.Error
            }catch (e: HttpException){
                HomeKursusUiState.Error
            }
        }
    }
}