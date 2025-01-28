package com.example.a12_081.ui.CustomWidget

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.ui.ViewModel.Kursus.HomeKursusUiState
import com.example.a12_081.ui.ViewModel.Kursus.HomeKursusViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.HomeSiswaUiState
import com.example.a12_081.ui.ViewModel.Siswa.HomeSiswaViewModel

data class SiswaItem(val id: String, val nama: String)

@Composable
fun DataNamaSiswa(
    NamaSwviewModel: HomeSiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
): List<SiswaItem> {
    // Mengambil state UI dari ViewModel
    val swUiState = NamaSwviewModel.swUiState

    // Mengolah data berdasarkan state
    return when (swUiState) {
        is HomeSiswaUiState.Success -> {
            val listSiswa = swUiState.siswa
            listSiswa.map { siswa -> SiswaItem(siswa.id_siswa, siswa.nama_siswa) }
        }
        is HomeSiswaUiState.Error -> {
            // Jika terjadi error, mengembalikan daftar kosong
            emptyList()
        }
        is HomeSiswaUiState.Loading -> {
            // Jika dalam proses loading, mengembalikan daftar kosong atau loading indicator
            emptyList()
        }
    }
}