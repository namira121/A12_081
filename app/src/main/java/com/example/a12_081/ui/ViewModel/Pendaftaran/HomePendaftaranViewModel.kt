package com.example.a12_081.ui.ViewModel.Pendaftaran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.a12_081.model.pendaftaran
import com.example.a12_081.repository.PendaftaranRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeDaftarUiState{
    data class Success(val pendaftaran: List<pendaftaran>): HomeDaftarUiState()
    object Error : HomeDaftarUiState()
    object Loading : HomeDaftarUiState()
}

class HomePendaftaranViewModel(
    private val dft: PendaftaranRepository): ViewModel(){
    var dftUiState: HomeDaftarUiState by mutableStateOf(HomeDaftarUiState.Loading)
        private set

    init {
        getPendaftaran()
    }

    fun getPendaftaran(){
        viewModelScope.launch {
            dftUiState = HomeDaftarUiState.Loading
            dftUiState = try {
                HomeDaftarUiState.Success(dft.getPendaftaran().data)
            }catch (e: IOException){
                HomeDaftarUiState.Error
            }catch (e: HttpException){
                HomeDaftarUiState.Error
            }
        }
    }

    fun deletePendaftaran(id_pendaftaran: String){
        viewModelScope.launch {
            try {
                dft.deletePendaftaran(id_pendaftaran)
            }catch (e: IOException){
                HomeDaftarUiState.Error
            }catch (e: HttpException){
                HomeDaftarUiState.Error
            }
        }
    }
}