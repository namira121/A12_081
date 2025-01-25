package com.example.a12_081.model

import kotlinx.serialization.Serializable

@Serializable
data class instruktur (
    val id_instruktur: String,
    val nama_instruktur: String,
    val email: String,
    val nomor_telepon: String,
    val deskripsi: String,
)

@Serializable
data class instrukturResponse(
    val status: Boolean,
    val message: String,
    val data: List<instruktur>
)

@Serializable
data class instrukturDetailResponse(
    val status: Boolean,
    val message: String,
    val data: instruktur
)