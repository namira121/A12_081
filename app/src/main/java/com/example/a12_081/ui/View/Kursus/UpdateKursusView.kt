package com.example.a12_081.ui.View.Kursus

import com.example.a12_081.ui.Navigation.AlamatNavigasi

object DestinasiUpdateKursus: AlamatNavigasi {
    override val route= "UpdateKursus"
    override val titleRes= "Update Kursus"
    const val ID_KURSUS = "id_kursus"
    val routeWithArgs = "$route/{$ID_KURSUS}"
}