package com.example.a12_081.ui.View.Pendaftaran

import com.example.a12_081.ui.Navigation.AlamatNavigasi

object DestinasiDetailPendaftaran: AlamatNavigasi {
    override val route= "DetailPendaftaran"
    override val titleRes= "Detail Data Pendaftaran"
    const val ID_PENDAFTARAN = "id_pendaftaran"
    val routeWithArgs = "$route/{$ID_PENDAFTARAN}"
}