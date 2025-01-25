package com.example.a12_081.ui.ViewModel.Instruktur

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.a12_081.model.instruktur
import com.example.a12_081.model.siswa
import com.example.a12_081.repository.InstrukturRepository
import com.example.a12_081.repository.SiswaRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeInstrukturUiState{
    data class Success(val instruktur: List<instruktur>): HomeInstrukturUiState()
    object Error : HomeInstrukturUiState()
    object Loading : HomeInstrukturUiState()
}

class HomeInstrukturViewModel(private val ins: InstrukturRepository): ViewModel(){
    var insUiState: HomeInstrukturUiState by mutableStateOf(HomeInstrukturUiState.Loading)
        private set

    init {
        getInstruktur()
    }

    fun getInstruktur(){
        viewModelScope.launch {
            insUiState = HomeInstrukturUiState.Loading
            insUiState = try {
                HomeInstrukturUiState.Success(ins.getInstruktur().data)
            }catch (e: IOException){
                HomeInstrukturUiState.Error
            }catch (e: HttpException){
                HomeInstrukturUiState.Error
            }
        }
    }

    fun deleteInstruktur(id_instruktur: String){
        viewModelScope.launch {
            try {
                ins.deleteInstruktur(id_instruktur)
            }catch (e: IOException){
                HomeInstrukturUiState.Error
            }catch (e: HttpException){
                HomeInstrukturUiState.Error
            }
        }
    }
}