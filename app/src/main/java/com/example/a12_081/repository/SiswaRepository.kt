package com.example.a12_081.repository

import com.example.a12_081.model.siswa
import com.example.a12_081.model.siswaResponse
import com.example.a12_081.service.SiswaService
import okio.IOException

interface SiswaRepository{
    suspend fun getSiswa(): siswaResponse
    suspend fun insertSiswa(siswa: siswa)
    suspend fun updateSiswa(id_siswa:String, siswa: siswa)
    suspend fun deleteSiswa(id_siswa: String)
    suspend fun getSiswaByID(id_siswa: String): siswa
}

class NetworkSiswaRepository(
    private val SiswaApiService: SiswaService
):SiswaRepository{
    override suspend fun getSiswa(): siswaResponse {
        return SiswaApiService.getSiswa()
    }

    override suspend fun insertSiswa(siswa: siswa) {
        SiswaApiService.insertSiswa(siswa)
    }

    override suspend fun updateSiswa(id_siswa: String, siswa: siswa) {
        SiswaApiService.updateSiswa(id_siswa, siswa)
    }

    override suspend fun deleteSiswa(id_siswa: String) {
        try {
            val response = SiswaApiService.deleteSiswa(id_siswa)
            if (!response.isSuccessful){
                throw IOException("Failed to delete siswa, HTTP status code: " +
                "${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getSiswaByID(id_siswa: String): siswa {
        return SiswaApiService.getSiswaByID(id_siswa).data
    }


}