package com.example.a12_081.ui.View.Siswa

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.model.siswa
import com.example.a12_081.ui.CustomWidget.CustomTopAppBar
import com.example.a12_081.ui.Navigation.AlamatNavigasi
import com.example.a12_081.ui.ViewModel.PenyediaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.DetailSiswaUiState
import com.example.a12_081.ui.ViewModel.Siswa.DetailSiswaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.toSw

object DestinasiDetailSiswa: AlamatNavigasi {
    override val route= "DetailSiswa"
    override val titleRes= "Detail Data Siswa"
    const val ID_SISWA = "id_siswa"
    val routeWithArgs = "$route/{$ID_SISWA}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSiswaView(
    modifier: Modifier = Modifier,
    NavigateBack: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit = { },
    viewModel: DetailSiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiDetailSiswa.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = NavigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onEditClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Siswa"
                )
            }
        }
    ) { innerPadding ->
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

        BodyDetailSiswa(
            detailSiswaUiState = viewModel.detailSiswaUiState,
            modifier = Modifier.padding(innerPadding),
            onDeleteClick = {
                deleteConfirmationRequired = true
            }
        )

        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    viewModel.deleteSw()
                    onDeleteClick()
                    deleteConfirmationRequired = false
                },
                onDeleteCancel = {
                    deleteConfirmationRequired = false
                },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun BodyDetailSiswa(
    modifier: Modifier = Modifier,
    detailSiswaUiState: DetailSiswaUiState,
    onDeleteClick: () -> Unit
) {
    when {
        detailSiswaUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailSiswaUiState.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailSiswaUiState.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailSiswaUiState.isUiEventNoEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailSiswa(
                    siswa = detailSiswaUiState.detailSiswaUiEvent.toSw(),
                    modifier = modifier
                )

                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = onDeleteClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Delete")
                }
            }
        }
    }
}

@Composable
fun ItemDetailSiswa(
    modifier: Modifier = Modifier,
    siswa: siswa,
){
    Card(
        modifier = modifier.fillMaxWidth().padding(top = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            ComponentDetailSw(judul = "ID", isinya = siswa.id_siswa)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailSw(judul = "Nama", isinya = siswa.nama_siswa)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailSw(judul = "Email", isinya = siswa.email)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailSw(judul = "Nomor Telepon", isinya = siswa.nomor_telepon)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun ComponentDetailSw(
    modifier: Modifier = Modifier,
    judul:String,
    isinya:String
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = {  },
        title = { Text("Delete Data")},
        text = { Text("Apakah anda yakin ingin menghapus data?")},
        dismissButton = {
            TextButton(onClick = { onDeleteCancel() }) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = { onDeleteConfirm() }) {
                Text(text = "Yes")
            }
        }
    )
}
