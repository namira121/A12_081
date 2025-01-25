package com.example.a12_081.service

import com.example.a12_081.model.instruktur
import com.example.a12_081.model.instrukturDetailResponse
import com.example.a12_081.model.instrukturResponse
import com.example.a12_081.model.kursus
import com.example.a12_081.model.kursusDetailResponse
import com.example.a12_081.model.kursusResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface kursusService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET(".")
    suspend fun getKursus(): kursusResponse

    @GET("{id_kursus}")
    suspend fun getKursusByID(@Path("id_kursus") id_kursus: String) : kursusDetailResponse

    @POST("addkursus")
    suspend fun insertKursus(@Body kursus: kursus)

    @PUT("{id_kursus}")
    suspend fun updateKursus(@Path("id_kursus")id_kursus: String, @Body kursus: kursus)

    @DELETE("{id_kursus}")
    suspend fun deleteKursus(@Path("id_kursus") id_kursus: String): Response<Void>
}