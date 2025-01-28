package com.example.a12_081.ui.View.Pendaftaran

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.ui.CustomWidget.CustomTopAppBar
import com.example.a12_081.ui.CustomWidget.DataNamaKursus
import com.example.a12_081.ui.CustomWidget.DataNamaSiswa
import com.example.a12_081.ui.CustomWidget.DropDownKu
import com.example.a12_081.ui.Navigation.AlamatNavigasi
import com.example.a12_081.ui.ViewModel.Kursus.HomeKursusViewModel
import com.example.a12_081.ui.ViewModel.Pendaftaran.InsertDaftarUiEvent
import com.example.a12_081.ui.ViewModel.Pendaftaran.InsertDaftarUiState
import com.example.a12_081.ui.ViewModel.Pendaftaran.InsertPendaftaranViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.HomeSiswaViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale


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
    val NamaKrsviewModel: HomeKursusViewModel = viewModel(factory = PenyediaViewModel.Factory)
    val NamaSwviewModel: HomeSiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
    val listNamaKursus = DataNamaKursus(NamaKrsviewModel)
    val listNamaSiswa = DataNamaSiswa(NamaSwviewModel)

    // State untuk menyimpan nilai yang dipilih
    var selectedValueKursus by remember { mutableStateOf(insertDaftarUiEvent.id_kursus) } // Menggunakan id_instruktur sebagai nilai awal
    var selectedValueSiswa by remember { mutableStateOf(insertDaftarUiEvent.id_siswa) } // Menggunakan id_instruktur sebagai nilai awal

//    var currentDateTime by remember { mutableStateOf(getCurrentDateTimeString()) }
//
//    LaunchedEffect(Unit) {
//        while (true) {
//            currentDateTime = getCurrentDateTimeString()
//            kotlinx.coroutines.delay(1000) // Delay 1 detik
//        }
//    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val kategori = listOf("Saintek", "Soshum")
        val status = listOf("Lunas", "Belum Lunas")
        DropDownKu(
            selectedValue = selectedValueKursus,
            options = listNamaKursus.map { it.nama }, // Menampilkan nama_kursus
            label = "Kursus",
            onValueChangedEvent = { selected ->
                val selectedKursus = listNamaKursus.find { it.nama == selected }
                selectedValueKursus = selectedKursus?.id ?: "" // Menyimpan id_instruktur, jika tidak ditemukan, set ke string kosong
                onValueChange(insertDaftarUiEvent.copy(id_kursus = selectedKursus?.id ?: "")) // Memperbarui id_instruktur
            }
        )
        DropDownKu(
            selectedValue = selectedValueSiswa,
            options = listNamaSiswa.map { it.nama }, // Menampilkan nama_siswa
            label = "Siswa",
            onValueChangedEvent = { selected ->
                val selectedSiswa = listNamaSiswa.find { it.nama == selected }
                selectedValueSiswa = selectedSiswa?.id ?: "" // Menyimpan id_instruktur, jika tidak ditemukan, set ke string kosong
                onValueChange(insertDaftarUiEvent.copy(id_siswa = selectedSiswa?.id ?: "")) // Memperbarui id_instruktur
            }
        )

        OutlinedTextField(
            value = insertDaftarUiEvent.tanggal_pendaftaran, // Use the formatted string as value
            onValueChange = { onValueChange(insertDaftarUiEvent.copy(tanggal_pendaftaran = it)) },
            label = { Text("Tanggal Pendaftaran") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
        )
        Text(text = "Kategori")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            kategori.forEach { kt ->
                Row (
                    verticalAlignment =  Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = insertDaftarUiEvent.kategori == kt,
                        onClick = {
                            onValueChange(insertDaftarUiEvent.copy(kategori = kt))
                        },
                    )
                    Text(
                        text = kt,
                    )

                }
            }
        }
        Text(text = "Status")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            status.forEach { st ->
                Row (
                    verticalAlignment =  Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = insertDaftarUiEvent.status == st,
                        onClick = {
                            onValueChange(insertDaftarUiEvent.copy(status = st))
                        },
                    )
                    Text(
                        text = st,
                    )

                }
            }
        }

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

//fun getCurrentDateTimeString(): String {
//    val calendar = Calendar.getInstance()
//    val year = calendar.get(Calendar.YEAR)
//    val month = calendar.get(Calendar.MONTH) + 1 // Month is 0-indexed
//    val day = calendar.get(Calendar.DAY_OF_MONTH)
//    val hour = calendar.get(Calendar.HOUR_OF_DAY)
//    val minute = calendar.get(Calendar.MINUTE)
//    val second = calendar.get(Calendar.SECOND)
//
//    return String.format(Locale.getDefault(), "%04d-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second)
//}