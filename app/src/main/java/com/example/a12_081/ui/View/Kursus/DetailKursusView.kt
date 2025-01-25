package com.example.a12_081.ui.View.Kursus

import com.example.a12_081.ui.Navigation.AlamatNavigasi

object DestinasiDetailKursus: AlamatNavigasi {
    override val route= "DetailKursus"
    override val titleRes= "Detail Data Kursus"
    const val ID_KURSUS = "id_kursus"
    val routeWithArgs = "$route/{$ID_KURSUS}"
}