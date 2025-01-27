package com.example.a12_081.ui.View.Instruktur

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.R
import com.example.a12_081.model.instruktur
import com.example.a12_081.ui.CustomWidget.CustomTopAppBar
import com.example.a12_081.ui.Navigation.AlamatNavigasi
import com.example.a12_081.ui.ViewModel.Instruktur.HomeInstrukturUiState
import com.example.a12_081.ui.ViewModel.Instruktur.HomeInstrukturViewModel
import com.example.a12_081.ui.ViewModel.PenyediaViewModel

object DestinasiHomeInstruktur: AlamatNavigasi {
    override val route= "HomeInstruktur"
    override val titleRes = "Home Instruktur"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeInstruktur(
    navigateBack: ()-> Unit,
    navigateToInsert: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeInstrukturViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHomeInstruktur.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getInstruktur()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToInsert,
                shape= MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Instruktur")
            }
        }
    ){innerpadding ->
        HomeInstrukturStatus(
            homeInstrukturUiState= viewModel.insUiState,
            retryAction={viewModel.getInstruktur()},
            modifier = Modifier.padding(innerpadding),
            onDetailClick = onDetailClick,
            onDeleteClick= {
                viewModel.deleteInstruktur(it.id_instruktur)
                viewModel.getInstruktur()
            }
        )
    }
}

@Composable
fun HomeInstrukturStatus(
    homeInstrukturUiState: HomeInstrukturUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (instruktur) -> Unit = {}
){
    when(homeInstrukturUiState){
        is HomeInstrukturUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeInstrukturUiState.Success ->
            if(homeInstrukturUiState.instruktur.isEmpty()){
                return Box(modifier= modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Instruktur")
                }
            }else{
                InsLayout(
                    instruktur=homeInstrukturUiState.instruktur, modifier=modifier.fillMaxWidth(),
                    onDetailClick ={
                        onDetailClick(it.id_instruktur)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeInstrukturUiState.Error -> OnError(retryAction, modifier= modifier.fillMaxSize())
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
fun InsLayout(
    instruktur: List<instruktur>,
    modifier: Modifier = Modifier,
    onDetailClick: (instruktur) -> Unit,
    onDeleteClick: (instruktur) -> Unit = {}
){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(instruktur){ ins ->
            InsCard(
                instruktur = ins,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(ins) },
                onDeleteClick = {
                    onDeleteClick(ins)
                }
            )
        }
    }
}

@Composable
fun InsCard(
    instruktur: instruktur,
    modifier: Modifier = Modifier,
    onDeleteClick: (instruktur) -> Unit = {}
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
                    text = instruktur.nama_instruktur,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {onDeleteClick(instruktur)}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
                Text(
                    text = instruktur.id_instruktur,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = instruktur.email,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = instruktur.nomor_telepon,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = instruktur.deskripsi,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}