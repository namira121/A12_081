package com.example.a12_081.ui.View.Kursus

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.R
import com.example.a12_081.model.kursus
import com.example.a12_081.ui.CustomWidget.CustomTopAppBar
import com.example.a12_081.ui.Navigation.AlamatNavigasi
import com.example.a12_081.ui.ViewModel.Kursus.HomeKursusUiState
import com.example.a12_081.ui.ViewModel.Kursus.HomeKursusViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel

object DestinasiHomeKursus: AlamatNavigasi {
    override val route= "HomeKursus"
    override val titleRes = "Home Kursus"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeKursus(
    navigateBack: ()-> Unit,
    navigateToInsert: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeKursusViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var searchQuery by remember { mutableStateOf("") }


    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHomeKursus.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getKursus()
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
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Kursus")
            }
        }
    ){innerpadding ->
        Column(modifier = Modifier.padding(innerpadding).nestedScroll(scrollBehavior.nestedScrollConnection)) {
            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    viewModel.searchKursus(it) // Panggil fungsi pencarian dengan nama_kursus setiap kali nilai berubah
                },
                placeholder = { Text("Cari kursus...") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search)
            )

            HomeKursusStatus(
                homeKursusUiState = viewModel.krsUiState,
                retryAction = { viewModel.getKursus() },
                modifier = Modifier.fillMaxWidth(),
                onDetailClick = onDetailClick,
                onDeleteClick = {
                    viewModel.deleteKursus(it.id_kursus)
                    viewModel.getKursus()
                },
                searchQuery = searchQuery // Pastikan searchQuery diteruskan
            )
        }
    }
}

@Composable
fun HomeKursusStatus(
    homeKursusUiState: HomeKursusUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (kursus) -> Unit = {},
    searchQuery: String // Pastikan searchQuery diteruskan
) {
    when (homeKursusUiState) {
        is HomeKursusUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeKursusUiState.Success -> {
            val filteredKursus = homeKursusUiState.kursus.filter { kursus ->
                kursus.nama_kursus.contains(searchQuery, ignoreCase = true) ||
                        kursus.kategori.contains(searchQuery, ignoreCase = true) ||
                        kursus.id_instruktur.contains(searchQuery, ignoreCase = true)
            }

            if (filteredKursus.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada hasil ditemukan untuk '$searchQuery'")
                }
            } else {
                KrsLayout(
                    kursus = filteredKursus,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_kursus)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        }

        is HomeKursusUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())

        else -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Terjadi kesalahan yang tidak terduga.")
            }
        }
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
fun KrsLayout(
    kursus: List<kursus>,
    modifier: Modifier = Modifier,
    onDetailClick: (kursus) -> Unit,
    onDeleteClick: (kursus) -> Unit = {}
){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(kursus){ krs ->
            KrsCard(
                kursus = krs,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(krs) },
                onDeleteClick = {
                    onDeleteClick(krs)
                }
            )
        }
    }
}

@Composable
fun KrsCard(
    kursus: kursus,
    modifier: Modifier = Modifier,
    onDeleteClick: (kursus) -> Unit = {}
) {
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
                    text = kursus.nama_kursus,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(kursus) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
                Text(
                    text = kursus.id_kursus,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Menampilkan ikon berdasarkan kategori
                when (kursus.kategori) {
                    "Saintek" -> {
                        Image(
                            painter = painterResource(id = R.drawable.sigma), // Ganti dengan resource ikon sigma Anda
                            contentDescription = "Ikon Saintek",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    "Soshum" -> {
                        Image(
                            painter = painterResource(id = R.drawable.buku), // Ganti dengan resource ikon buku Anda
                            contentDescription = "Ikon Soshum",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = kursus.kategori,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = kursus.deskripsi,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = kursus.id_instruktur,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}