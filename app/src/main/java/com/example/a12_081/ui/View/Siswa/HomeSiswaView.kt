package com.example.a12_081.ui.View.Siswa

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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a12_081.R
import com.example.a12_081.model.siswa
import com.example.a12_081.ui.CustomWidget.CustomTopAppBar
import com.example.a12_081.ui.Navigation.AlamatNavigasi
import com.example.a12_081.ui.ViewModel.PenyediaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.DetailSiswaViewModel
import com.example.a12_081.ui.ViewModel.Siswa.HomeSiswaUiState
import com.example.a12_081.ui.ViewModel.Siswa.HomeSiswaViewModel

object DestinasiHomeSiswa: AlamatNavigasi{
    override val route= "HomeSiswa"
    override val titleRes = "Home Siswa"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSiswa(
    navigateBack: ()->Unit,
    navigateToInsert: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeSiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHomeSiswa.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getSiswa()
                }
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
        HomeSiswaStatus(
            homeSiswaUiState= viewModel.swUiState,
            retryAction={viewModel.getSiswa()},
            modifier = Modifier.padding(innerpadding),
            onDetailClick = onDetailClick,
            onDeleteClick= {
                viewModel.deleteSiswa(it.id_siswa)
                viewModel.getSiswa()
            }
        )
    }
}

@Composable
fun HomeSiswaStatus(
    homeSiswaUiState: HomeSiswaUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (siswa) -> Unit = {}
){
    when(homeSiswaUiState){
        is HomeSiswaUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeSiswaUiState.Success ->
            if(homeSiswaUiState.siswa.isEmpty()){
                return Box(modifier= modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Siswa")
                }
            }else{
                SwLayout(
                    siswa=homeSiswaUiState.siswa, modifier=modifier.fillMaxWidth(),
                    onDetailClick ={
                        onDetailClick(it.id_siswa)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeSiswaUiState.Error -> OnError(retryAction, modifier= modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier=Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.pending),
        contentDescription = "Loading"
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier=Modifier){
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
fun SwLayout(
    siswa: List<siswa>,
    modifier: Modifier = Modifier,
    onDetailClick: (siswa) -> Unit,
    onDeleteClick: (siswa) -> Unit = {}
){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(siswa){ sw ->
            SwCard(
                siswa = sw,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(sw) },
                onDeleteClick = {
                    onDeleteClick(sw)
                }
            )
        }
    }
}

@Composable
fun SwCard(
    siswa: siswa,
    modifier: Modifier =Modifier,
    onDeleteClick: (siswa) -> Unit = {}
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
                    text = siswa.nama_siswa,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {onDeleteClick(siswa)}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
                Text(
                    text = siswa.id_siswa,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = siswa.email,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = siswa.nomor_telepon,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

