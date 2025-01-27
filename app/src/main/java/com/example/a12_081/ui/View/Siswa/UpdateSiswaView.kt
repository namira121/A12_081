package com.example.a12_081.ui.View.Siswa

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.ui.CustomWidget.CustomTopAppBar
import com.example.a12_081.ui.Navigation.AlamatNavigasi
import com.example.a12_081.ui.ViewModel.PenyediaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.UpdateSiswaViewModel
import kotlinx.coroutines.launch

object DestinasiUpdateSiswa: AlamatNavigasi {
    override val route= "UpdateSiswa"
    override val titleRes= "Update Siswa"
    const val ID_SISWA = "id_siswa"
    val routeWithArgs = "$route/{$ID_SISWA}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateSiswaView(
    id_siswa: String,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    updateViewModel: UpdateSiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = DestinasiUpdateSiswa.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        InsertBody(
            insertSiswaUiState = updateViewModel.updateSiswaUiState,
            onSiswaValueChange  = updateViewModel::updateInsertSwState,
            onSaveClick = {
                coroutineScope.launch {
                    updateViewModel.updateSw()
                    onNavigateUp()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}