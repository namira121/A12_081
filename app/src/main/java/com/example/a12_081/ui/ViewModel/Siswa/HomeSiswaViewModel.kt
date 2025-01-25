package com.example.a12_081.ui.ViewModel.Siswa

import coil.network.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a12_081.model.siswa
import com.example.a12_081.repository.SiswaRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeSiswaUiState{
    data class Success(val siswa: List<siswa>): HomeSiswaUiState()
    object Error : HomeSiswaUiState()
    object Loading : HomeSiswaUiState()
}

class HomeSiswaViewModel(private val sw: SiswaRepository): ViewModel(){
    var swUiState: HomeSiswaUiState by mutableStateOf(HomeSiswaUiState.Loading)
        private set

    init {
        getSiswa()
    }

    fun getSiswa(){
        viewModelScope.launch {
            swUiState = HomeSiswaUiState.Loading
            swUiState = try {
                HomeSiswaUiState.Success(sw.getSiswa().data)
            }catch (e:IOException){
                HomeSiswaUiState.Error
            }catch (e:HttpException){
                HomeSiswaUiState.Error
            }
        }
    }

    fun deleteSiswa(id_siswa: String){
        viewModelScope.launch {
            try {
                sw.deleteSiswa(id_siswa)
            }catch (e:IOException){
                HomeSiswaUiState.Error
            }catch (e:HttpException){
                HomeSiswaUiState.Error
            }
        }
    }
}