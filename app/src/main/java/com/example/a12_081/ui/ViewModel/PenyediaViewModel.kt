package com.example.a12_081.ui.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.transition.Transition
import com.example.a12_081.BimbelApplications
import com.example.a12_081.ui.ViewModel.Instruktur.HomeInstrukturViewModel
import com.example.a12_081.ui.ViewModel.Siswa.DetailSiswaUiState
import com.example.a12_081.ui.ViewModel.Siswa.DetailSiswaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.HomeSiswaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.InsertSiswaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.UpdateSiswaViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer { HomeSiswaViewModel(BimbelApp().container.siswaRepository) }
        initializer { InsertSiswaViewModel(BimbelApp().container.siswaRepository) }
        initializer { UpdateSiswaViewModel(createSavedStateHandle(),BimbelApp().container.siswaRepository) }
        initializer { DetailSiswaViewModel(createSavedStateHandle(),BimbelApp().container.siswaRepository) }

        initializer { HomeInstrukturViewModel(BimbelApp().container.instrukturRepository) }

    }
    fun CreationExtras.BimbelApp():BimbelApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as BimbelApplications)
}