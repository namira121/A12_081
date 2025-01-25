package com.example.a12_081.service

import com.example.a12_081.model.instruktur
import com.example.a12_081.model.instrukturDetailResponse
import com.example.a12_081.model.instrukturResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface instrukturService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET(".")
    suspend fun getInstruktur(): instrukturResponse

    @GET("{id_instruktur}")
    suspend fun getInstrukturByID(@Path("id_instruktur") id_instruktur: String) : instrukturDetailResponse

    @POST("addinstruktur")
    suspend fun insertInstruktur(@Body instruktur: instruktur)

    @PUT("{id_instruktur}")
    suspend fun updateInstruktur(@Path("id_instruktur")id_instruktur: String, @Body instruktur: instruktur)

    @DELETE("{id_instruktur}")
    suspend fun deleteInstruktur(@Path("id_instruktur") id_instruktur: String): Response<Void>
}