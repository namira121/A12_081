package com.example.a12_081.ui.View.Siswa

import com.example.a12_081.ui.Navigation.AlamatNavigasi

object DestinasiUpdateSiswa: AlamatNavigasi {
    override val route= "UpdateSiswa"
    override val titleRes= "Update Siswa"
    const val ID_SISWA = "id_siswa"
    val routeWithArgs = "$route/{$ID_SISWA}"
}