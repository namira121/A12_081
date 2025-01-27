package com.example.a12_081.ui.View.Kursus

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
import com.example.a12_081.ui.ViewModel.Kursus.InsertKursusUiEvent
import com.example.a12_081.ui.ViewModel.Kursus.InsertKursusUiState
import com.example.a12_081.ui.ViewModel.Kursus.InsertKursusViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.InsertSiswaUiEvent
import com.example.a12_081.ui.ViewModel.Siswa.InsertSiswaUiState
import com.example.a12_081.ui.ViewModel.Siswa.InsertSiswaViewModel
import kotlinx.coroutines.launch

object DestinasiInsertKursus: AlamatNavigasi {
    override val route= "InsertKursus"
    override val titleRes = "Tambah Kursus"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertKursusView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertKursusViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier=modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiInsertKursus.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) {innerPadding ->
        InsertKursusBody(
            insertKursusUiState = viewModel.krsUiState,
            onKursusValueChange = viewModel::updateInsertKursusState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertKrs()
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
fun InsertKursusBody(
    insertKursusUiState: InsertKursusUiState,
    onKursusValueChange:(InsertKursusUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormKursusInput(
            insertKursusUiEvent = insertKursusUiState.insertKursusUiEvent,
            onValueChange = onKursusValueChange,
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
fun FormKursusInput(
    insertKursusUiEvent: InsertKursusUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertKursusUiEvent) -> Unit = {},
    enabled: Boolean =true
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertKursusUiEvent.id_kursus,
            onValueChange = {onValueChange(insertKursusUiEvent.copy(id_kursus = it))},
            label = { Text("ID") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertKursusUiEvent.nama_kursus,
            onValueChange = {onValueChange(insertKursusUiEvent.copy(nama_kursus = it))},
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertKursusUiEvent.kategori,
            onValueChange = {onValueChange(insertKursusUiEvent.copy(kategori = it))},
            label = { Text("Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertKursusUiEvent.harga,
            onValueChange = {onValueChange(insertKursusUiEvent.copy(harga = it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertKursusUiEvent.deskripsi,
            onValueChange = {onValueChange(insertKursusUiEvent.copy(deskripsi = it))},
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertKursusUiEvent.id_instruktur,
            onValueChange = {onValueChange(insertKursusUiEvent.copy(id_instruktur = it))},
            label = { Text("Instruktur") },
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