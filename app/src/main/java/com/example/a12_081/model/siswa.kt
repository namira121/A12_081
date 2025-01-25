package com.example.a12_081.model

import kotlinx.serialization.Serializable

@Serializable
data class siswa (
    val id_siswa: String,
    val nama_siswa: String,
    val email: String,
    val nomor_telepon: String
)

@Serializable
data class siswaResponse(
    val status: Boolean,
    val message: String,
    val data: List<siswa>
)

@Serializable
data class siswaDetailResponse(
    val status: Boolean,
    val message: String,
    val data: siswa
)