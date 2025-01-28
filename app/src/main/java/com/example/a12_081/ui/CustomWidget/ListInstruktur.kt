package com.example.a12_081.ui.CustomWidget

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.ui.ViewModel.Instruktur.HomeInstrukturUiState
import com.example.a12_081.ui.ViewModel.Instruktur.HomeInstrukturViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel

data class InstrukturItem(val id: String, val nama: String)

object ListInstruktur {
    @Composable
    fun DataNamaInstruktur(
        NamaInsviewModel: HomeInstrukturViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<InstrukturItem> {
        // Mengambil state UI dari ViewModel
        val insUiState = NamaInsviewModel.insUiState

        // Mengolah data berdasarkan state
        return when (insUiState) {
            is HomeInstrukturUiState.Success -> {
                val listInstruktur = insUiState.instruktur
                listInstruktur.map { instruktur -> InstrukturItem(instruktur.id_instruktur, instruktur.nama_instruktur) }
            }
            is HomeInstrukturUiState.Error -> {
                // Jika terjadi error, mengembalikan daftar kosong
                emptyList()
            }
            is HomeInstrukturUiState.Loading -> {
                // Jika dalam proses loading, mengembalikan daftar kosong atau loading indicator
                emptyList()
            }
        }
    }
}