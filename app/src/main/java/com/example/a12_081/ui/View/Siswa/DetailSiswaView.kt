package com.example.a12_081.ui.View.Siswa

import com.example.a12_081.ui.Navigation.AlamatNavigasi

object DestinasiDetailSiswa: AlamatNavigasi {
    override val route= "DetailSiswa"
    override val titleRes= "Detail Data Siswa"
    const val ID_SISWA = "id_siswa"
    val routeWithArgs = "$route/{$ID_SISWA}"
}