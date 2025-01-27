package com.example.a12_081.ui.View.Instruktur

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.ui.CustomWidget.CustomTopAppBar
import com.example.a12_081.ui.Navigation.AlamatNavigasi
import com.example.a12_081.ui.View.Siswa.DestinasiInsertSiswa
import com.example.a12_081.ui.ViewModel.Instruktur.InsertInstrukturUiEvent
import com.example.a12_081.ui.ViewModel.Instruktur.InsertInstrukturUiState
import com.example.a12_081.ui.ViewModel.Instruktur.InsertInstrukturViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.InsertSiswaUiEvent
import com.example.a12_081.ui.ViewModel.Siswa.InsertSiswaUiState
import com.example.a12_081.ui.ViewModel.Siswa.InsertSiswaViewModel
import kotlinx.coroutines.launch

object DestinasiInsertInstruktur: AlamatNavigasi {
    override val route= "InsertInstruktur"
    override val titleRes = "Tambah Instruktur"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertInstrukturView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertInstrukturViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier=modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiInsertInstruktur.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) {innerPadding ->
        InsertInstrukturBody(
            insertInstrukturUiState = viewModel.InsUiState,
            onInstrukturValueChange = viewModel::updateInsertInsState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertIns()
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
fun InsertInstrukturBody(
    insertInstrukturUiState: InsertInstrukturUiState,
    onInstrukturValueChange:(InsertInstrukturUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInstrukturInput(
            insertInstrukturUiEvent = insertInstrukturUiState.insertInstrukturUiEvent,
            onValueChange = onInstrukturValueChange,
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
fun FormInstrukturInput(
    insertInstrukturUiEvent: InsertInstrukturUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertInstrukturUiEvent) -> Unit = {},
    enabled: Boolean =true
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertInstrukturUiEvent.id_instruktur,
            onValueChange = {onValueChange(insertInstrukturUiEvent.copy(id_instruktur = it))},
            label = { Text("ID") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertInstrukturUiEvent.nama_instruktur,
            onValueChange = {onValueChange(insertInstrukturUiEvent.copy(nama_instruktur = it))},
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertInstrukturUiEvent.email,
            onValueChange = {onValueChange(insertInstrukturUiEvent.copy(email = it))},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertInstrukturUiEvent.nomor_telepon,
            onValueChange = {onValueChange(insertInstrukturUiEvent.copy(nomor_telepon = it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Nomor Telepon") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertInstrukturUiEvent.deskripsi,
            onValueChange = {onValueChange(insertInstrukturUiEvent.copy(deskripsi = it))},
            label = { Text("Deskripsi") },
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