package com.example.a12_081.service

import com.example.a12_081.model.siswa
import com.example.a12_081.model.siswaDetailResponse
import com.example.a12_081.model.siswaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface siswaService{
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET(".")
    suspend fun getSiswa(): siswaResponse

    @GET("{id_siswa}")
    suspend fun getSiswaByID(@Path("id_siswa") id_siswa: String) :siswaDetailResponse

    @POST("addsiswa")
    suspend fun insertSiswa(@Body siswa: siswa)

    @PUT("{id_siswa}")
    suspend fun updateSiswa(@Path("id_siswa")id_siswa: String, @Body siswa: siswa)

    @DELETE("{id_siswa}")
    suspend fun deleteSiswa(@Path("id_siswa") id_siswa: String):Response <Void>
}