package com.example.a12_081.ui.View.Kursus

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.ui.CustomWidget.CustomTopAppBar
import com.example.a12_081.ui.Navigation.AlamatNavigasi
import com.example.a12_081.ui.View.Pendaftaran.DestinasiUpdatePendaftaran
import com.example.a12_081.ui.View.Pendaftaran.InsertDaftarBody
import com.example.a12_081.ui.ViewModel.Kursus.UpdateKursusViewModel
import com.example.a12_081.ui.ViewModel.Pendaftaran.UpdateDaftarViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiUpdateKursus: AlamatNavigasi {
    override val route= "UpdateKursus"
    override val titleRes= "Update Kursus"
    const val ID_KURSUS = "id_kursus"
    val routeWithArgs = "$route/{$ID_KURSUS}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateKursusView(
    id_kursus: String,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    updateViewModel: UpdateKursusViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = DestinasiUpdateKursus.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        InsertKursusBody(
            insertKursusUiState = updateViewModel.updateKursusUiState,
            onKursusValueChange = updateViewModel::updateInsertKrsState,
            onSaveClick = {
                coroutineScope.launch {
                    updateViewModel.updateKrs()
                    onNavigateUp()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}