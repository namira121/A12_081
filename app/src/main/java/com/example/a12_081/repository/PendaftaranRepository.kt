package com.example.a12_081.repository

import com.example.a12_081.model.pendaftaran
import com.example.a12_081.model.pendaftaranResponse
import com.example.a12_081.model.siswa
import com.example.a12_081.model.siswaResponse
import com.example.a12_081.service.PendaftaranService
import java.io.IOException

interface PendaftaranRepository{
    suspend fun getPendaftaran(): pendaftaranResponse
    suspend fun insertPendaftaran(pendaftaran: pendaftaran)
    suspend fun updatePendaftaran(id_pendaftaran:String, pendaftaran: pendaftaran)
    suspend fun deletePendaftaran(id_pendaftaran: String)
    suspend fun getPendaftaranByID(id_pendaftaran: String): pendaftaran
}

class NetworkPendaftaranRepository(
    private val PendaftaranApiService: PendaftaranService
):PendaftaranRepository{
    override suspend fun getPendaftaran(): pendaftaranResponse {
        return PendaftaranApiService.getPendaftaran()
    }

    override suspend fun insertPendaftaran(pendaftaran: pendaftaran) {
        PendaftaranApiService.insertPendaftaran(pendaftaran)
    }

    override suspend fun updatePendaftaran(id_pendaftaran: String, pendaftaran: pendaftaran) {
        PendaftaranApiService.updatePendaftaran(id_pendaftaran, pendaftaran)
    }

    override suspend fun deletePendaftaran(id_pendaftaran: String) {
        try{
            val response = PendaftaranApiService.deletePendaftaran(id_pendaftaran)
            if(!response.isSuccessful){
                throw IOException("Failed to delete Pendaftaran, HTTP status code: " +
                        "${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getPendaftaranByID(id_pendaftaran: String): pendaftaran {
        return PendaftaranApiService.getPendaftaranByID(id_pendaftaran).data
    }

}