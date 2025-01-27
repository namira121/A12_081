package com.example.a12_081.ui.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a12_081.R
import com.example.a12_081.ui.Navigation.AlamatNavigasi

object DestinasiHome: AlamatNavigasi {
    override val route= "Home"
    override val titleRes= "Home"
}

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    navigateHomeSiswa: () -> Unit,
    navigateHomeInstruktur: () -> Unit,
    navigateHomeKursus: () -> Unit,
    navigateHomePendaftaran: () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.purple_200)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.prima),
                contentDescription = "",
                modifier = Modifier.size(45.dp).clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.padding(start = 16.dp))
            Column {
                Text(
                    text = "Bimbel Prima",
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Masuk PTN Keinginanmu dengan Prima",
                    color = Color.Blue,
                    fontWeight = FontWeight.Light
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp)
                )
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = navigateHomeSiswa
                ) {
                    Text(text = "Siswa")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = navigateHomeInstruktur
                ) {
                    Text(text = "Instruktur")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = navigateHomeKursus
                ) {
                    Text(text = "Kursus")
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = navigateHomePendaftaran
                ) {
                    Text(text = "Pendaftaran")
                }
            }
        }
    }
}