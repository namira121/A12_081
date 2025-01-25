package com.example.a12_081.ui.View.Pendaftaran

import com.example.a12_081.ui.Navigation.AlamatNavigasi

object DestinasiUpdatePendaftaran: AlamatNavigasi {
    override val route= "UpdatePendaftaran"
    override val titleRes= "Update Pendaftaran"
    const val ID_PENDAFTARAN = "id_pendaftaran"
    val routeWithArgs = "$route/{$ID_PENDAFTARAN}"
}