package com.example.a12_081.ui.View.Pendaftaran

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.ui.CustomWidget.CustomTopAppBar
import com.example.a12_081.ui.Navigation.AlamatNavigasi
import com.example.a12_081.ui.ViewModel.Pendaftaran.InsertDaftarUiEvent
import com.example.a12_081.ui.ViewModel.Pendaftaran.InsertDaftarUiState
import com.example.a12_081.ui.ViewModel.Pendaftaran.InsertPendaftaranViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiInsertPendaftaran: AlamatNavigasi {
    override val route= "InsertPendaftaran"
    override val titleRes = "Tambah Pendaftaran"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertPendaftaranView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPendaftaranViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier=modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiInsertPendaftaran.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) {innerPadding ->
        InsertDaftarBody(
            insertDaftarUiState = viewModel.dtrUiState,
            onDaftarValueChange = viewModel::updateInsertDaftarState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertDtr()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun InsertDaftarBody(
    insertDaftarUiState: InsertDaftarUiState,
    onDaftarValueChange:(InsertDaftarUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormDaftarInput(
            insertDaftarUiEvent = insertDaftarUiState.insertDaftarUiEvent,
            onValueChange = onDaftarValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormDaftarInput(
    insertDaftarUiEvent: InsertDaftarUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertDaftarUiEvent) -> Unit = {},
    enabled: Boolean =true
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertDaftarUiEvent.id_pendaftaran,
            onValueChange = {onValueChange(insertDaftarUiEvent.copy(id_pendaftaran = it))},
            label = { Text("ID Pendaftaran ") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertDaftarUiEvent.id_siswa,
            onValueChange = {onValueChange(insertDaftarUiEvent.copy(id_siswa = it))},
            label = { Text("ID Siswa") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertDaftarUiEvent.id_kursus,
            onValueChange = {onValueChange(insertDaftarUiEvent.copy(id_kursus = it))},
            label = { Text("ID Kursus") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertDaftarUiEvent.tanggal_pendaftaran,
            onValueChange = {onValueChange(insertDaftarUiEvent.copy(tanggal_pendaftaran = it))},
            label = { Text("Tanggal Pendaftaran") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled){
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}