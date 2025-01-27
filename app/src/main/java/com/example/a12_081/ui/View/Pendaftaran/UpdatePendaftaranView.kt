package com.example.a12_081.ui.View.Pendaftaran

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.ui.CustomWidget.CustomTopAppBar
import com.example.a12_081.ui.Navigation.AlamatNavigasi
import com.example.a12_081.ui.View.Siswa.InsertBody
import com.example.a12_081.ui.ViewModel.Pendaftaran.UpdateDaftarViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.UpdateSiswaViewModel
import kotlinx.coroutines.launch

object DestinasiUpdatePendaftaran: AlamatNavigasi {
    override val route= "UpdatePendaftaran"
    override val titleRes= "Update Pendaftaran"
    const val ID_PENDAFTARAN = "id_pendaftaran"
    val routeWithArgs = "$route/{$ID_PENDAFTARAN}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePendaftaranView(
    id_pendaftaran: String,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    updateViewModel: UpdateDaftarViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = DestinasiUpdatePendaftaran.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        InsertDaftarBody(
            insertDaftarUiState = updateViewModel.updateDaftarUiState,
            onDaftarValueChange  = updateViewModel::updateInsertDtrState,
            onSaveClick = {
                coroutineScope.launch {
                    updateViewModel.updateDtr()
                    onNavigateUp()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}