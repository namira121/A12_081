package com.example.a12_081.service


import com.example.a12_081.model.pendaftaran
import com.example.a12_081.model.pendaftaranDetailResponse
import com.example.a12_081.model.pendaftaranResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface pendaftaranService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET(".")
    suspend fun getPendaftaran(): pendaftaranResponse

    @GET("{id_pendaftaran}")
    suspend fun getPendaftaranByID(@Path("id_pendaftaran") id_pendaftaran: String) : pendaftaranDetailResponse

    @POST("addpendaftaran")
    suspend fun insertPendaftaran(@Body pendaftaran: pendaftaran)

    @PUT("{id_pendaftaran}")
    suspend fun updatePendaftaran(@Path("id_pendaftaran")id_pendaftaran: String, @Body pendaftaran: pendaftaran)

    @DELETE("{id_pendaftaran}")
    suspend fun deletePendaftaran(@Path("id_pendaftaran") id_pendaftaran: String): Response<Void>
}