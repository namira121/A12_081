package com.example.a12_081.ui.View.Instruktur

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.ui.CustomWidget.CustomTopAppBar
import com.example.a12_081.ui.Navigation.AlamatNavigasi
import com.example.a12_081.ui.View.Kursus.InsertKursusBody
import com.example.a12_081.ui.View.Pendaftaran.DestinasiUpdatePendaftaran
import com.example.a12_081.ui.View.Pendaftaran.InsertDaftarBody
import com.example.a12_081.ui.ViewModel.Instruktur.UpdateInstrukturViewModel
import com.example.a12_081.ui.ViewModel.Pendaftaran.UpdateDaftarViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiUpdateInstruktur: AlamatNavigasi {
    override val route= "UpdateInstruktur"
    override val titleRes= "Update Instruktur"
    const val ID_INSTRUKTUR = "id_instruktur"
    val routeWithArgs = "$route/{$ID_INSTRUKTUR}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateInstrukturView(
    id_instruktur: String,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    updateViewModel: UpdateInstrukturViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = DestinasiUpdateInstruktur.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        modifier = modifier
    ) { innerPadding ->
        InsertInstrukturBody(
            insertInstrukturUiState = updateViewModel.updateInstrukturUiState,
            onInstrukturValueChange  = updateViewModel::updateInsertInsState,
            onSaveClick = {
                coroutineScope.launch {
                    updateViewModel.updateIns()
                    onNavigateUp()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}