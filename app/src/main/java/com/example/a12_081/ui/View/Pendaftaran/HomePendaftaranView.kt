package com.example.a12_081.ui.View.Pendaftaran

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.R
import com.example.a12_081.model.pendaftaran
import com.example.a12_081.ui.CustomWidget.CustomTopAppBar
import com.example.a12_081.ui.Navigation.AlamatNavigasi
import com.example.a12_081.ui.ViewModel.Pendaftaran.HomeDaftarUiState
import com.example.a12_081.ui.ViewModel.Pendaftaran.HomePendaftaranViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel

object DestinasiHomePendaftaran: AlamatNavigasi {
    override val route= "HomePendaftaran"
    override val titleRes = "Home Pendaftaran"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePendaftaran(
    navigateBack: ()-> Unit,
    navigateToInsert: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomePendaftaranViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHomePendaftaran.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPendaftaran()
                },
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToInsert,
                shape= MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Siswa")
            }
        }
    ){innerpadding ->
        var selectedCategory by remember { mutableStateOf("Semua") }

        Column(modifier = Modifier.padding(innerpadding)){
            // Pilihan kategori
            Row {
                Text("Semua")
                RadioButton(
                    selected = selectedCategory == "",
                    onClick = { selectedCategory = "" }
                )
                Text("Saintek")
                RadioButton(
                    selected = selectedCategory == "Saintek",
                    onClick = { selectedCategory = "Saintek" }
                )
                Text("Soshum")
                RadioButton(
                    selected = selectedCategory == "Soshum",
                    onClick = { selectedCategory = "Soshum" }
                )
            }

            // Panggil HomeDaftarStatus dengan kategori yang dipilih
            HomeDaftarStatus(
                homeDaftarUiState = viewModel.dftUiState,
                retryAction = { viewModel.getPendaftaran() },
                modifier = Modifier.fillMaxSize(),
                onDetailClick = onDetailClick,
                onDeleteClick = {
                    viewModel.deletePendaftaran(it.id_pendaftaran)
                    viewModel.getPendaftaran()
                },
                selectedCategory= selectedCategory // Gunakan kategori yang dipilih
            )
        }
    }
}

@Composable
fun HomeDaftarStatus(
    homeDaftarUiState: HomeDaftarUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (pendaftaran) -> Unit = {},
    selectedCategory: String
) {
    when (homeDaftarUiState) {
        is HomeDaftarUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeDaftarUiState.Success -> {
            // Filter pendaftaran berdasarkan kategori
            val filteredPendaftaran = when (selectedCategory) {
                "" -> homeDaftarUiState.pendaftaran // Tampilkan semua data
                else -> homeDaftarUiState.pendaftaran.filter { pendaftaran ->
                    pendaftaran.id_kursus.contains(selectedCategory, ignoreCase = true)
                }
            }
            if (filteredPendaftaran.isEmpty()) {
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Pendaftaran")
                }
            } else {
                DftLayout(
                    pendaftaran = filteredPendaftaran,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_pendaftaran)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        }
        is HomeDaftarUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.pending),
        contentDescription = "Loading"
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier){
    Column(
        modifier=modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.gagal),
            contentDescription = "Error"
        )
        Text(text = "Failed",
            modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun DftLayout(
    pendaftaran: List<pendaftaran>,
    modifier: Modifier = Modifier,
    onDetailClick: (pendaftaran) -> Unit,
    onDeleteClick: (pendaftaran) -> Unit = {}
){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pendaftaran){ dft ->
            DftCard(
                pendaftaran = dft,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(dft) },
                onDeleteClick = {
                    onDeleteClick(dft)
                }
            )
        }
    }
}

@Composable
fun DftCard(
    pendaftaran: pendaftaran,
    modifier: Modifier = Modifier,
    onDeleteClick: (pendaftaran) -> Unit = {}
){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pendaftaran.id_pendaftaran,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {onDeleteClick(pendaftaran)}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
                Text(
                    text = pendaftaran.id_siswa,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = pendaftaran.id_kursus,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = pendaftaran.tanggal_pendaftaran,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}