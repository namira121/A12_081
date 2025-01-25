package com.example.a12_081.ui.View.Instruktur

import com.example.a12_081.ui.Navigation.AlamatNavigasi

object DestinasiDetailInstruktur: AlamatNavigasi {
    override val route= "DetailInstruktur"
    override val titleRes= "Detail Data Instruktur"
    const val ID_INSTRUKTUR = "id_instruktur"
    val routeWithArgs = "$route/{$ID_INSTRUKTUR}"
}