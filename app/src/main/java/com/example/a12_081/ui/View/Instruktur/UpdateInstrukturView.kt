package com.example.a12_081.ui.View.Instruktur

import com.example.a12_081.ui.Navigation.AlamatNavigasi

object DestinasiUpdateInstruktur: AlamatNavigasi {
    override val route= "UpdateInstruktur"
    override val titleRes= "Update Instruktur"
    const val ID_INSTRUKTUR = "id_instruktur"
    val routeWithArgs = "$route/{$ID_INSTRUKTUR}"
}