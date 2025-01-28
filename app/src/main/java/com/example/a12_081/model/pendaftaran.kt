package com.example.a12_081.model

import kotlinx.serialization.Serializable

@Serializable
data class pendaftaran (
    val id_pendaftaran: String,
    val id_siswa: String,
    val id_kursus: String,
    val tanggal_pendaftaran: String,
    val kategori:String,
    val status:String
)

@Serializable
data class pendaftaranResponse(
    val status: Boolean,
    val message: String,
    val data: List<pendaftaran>
)

@Serializable
data class pendaftaranDetailResponse(
    val status: Boolean,
    val message: String,
    val data: pendaftaran
)