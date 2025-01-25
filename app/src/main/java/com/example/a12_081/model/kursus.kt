package com.example.a12_081.model

import kotlinx.serialization.Serializable

@Serializable
data class kursus (
    val id_kursus: String,
    val nama_kursus: String,
    val deskripsi: String,
    val kategori: String,
    val harga: String,
    val id_instruktur: String
)

@Serializable
data class kursusResponse(
    val status: Boolean,
    val message: String,
    val data: List<kursus>
)

@Serializable
data class kursusDetailResponse(
    val status: Boolean,
    val message: String,
    val data: kursus
)