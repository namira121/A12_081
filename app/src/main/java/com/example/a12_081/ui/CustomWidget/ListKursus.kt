package com.example.a12_081.ui.CustomWidget

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.ui.ViewModel.Kursus.HomeKursusUiState
import com.example.a12_081.ui.ViewModel.Kursus.HomeKursusViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel

data class KursusItem(val id: String, val nama: String)

@Composable
fun DataNamaKursus(
    NamaKrsviewModel: HomeKursusViewModel = viewModel(factory = PenyediaViewModel.Factory)
): List<KursusItem> {
    // Mengambil state UI dari ViewModel
    val krsUiState = NamaKrsviewModel.krsUiState

    // Mengolah data berdasarkan state
    return when (krsUiState) {
        is HomeKursusUiState.Success -> {
            val listKursus = krsUiState.kursus
            listKursus.map { kursus -> KursusItem(kursus.id_kursus, kursus.nama_kursus) }
        }
        is HomeKursusUiState.Error -> {
            // Jika terjadi error, mengembalikan daftar kosong
            emptyList()
        }
        is HomeKursusUiState.Loading -> {
            // Jika dalam proses loading, mengembalikan daftar kosong atau loading indicator
            emptyList()
        }
    }
}
