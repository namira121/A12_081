package com.example.a12_081.repository

import com.example.a12_081.model.kursus
import com.example.a12_081.model.kursusResponse
import com.example.a12_081.service.kursusService
import java.io.IOException

interface KursusRepository{
    suspend fun getKursus(): kursusResponse
    suspend fun insertKursus(kursus: kursus)
    suspend fun updateKursus(id_kursus:String, kursus: kursus)
    suspend fun deleteKursus(id_kursus: String)
    suspend fun getKursusByID(id_kursus: String): kursus
}

class NetworkKursusRepository(
    private val KursusApiService: kursusService
):KursusRepository{
    override suspend fun getKursus(): kursusResponse {
       return KursusApiService.getKursus()
    }

    override suspend fun insertKursus(kursus: kursus) {
        KursusApiService.insertKursus(kursus)
    }

    override suspend fun updateKursus(id_kursus: String, kursus: kursus) {
        KursusApiService.updateKursus(id_kursus, kursus)
    }

    override suspend fun deleteKursus(id_kursus: String) {
        try{
            val response = KursusApiService.deleteKursus(id_kursus)
            if(!response.isSuccessful){
                throw IOException("Failed to delete kursus, HTTP status code: " +
                        "${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getKursusByID(id_kursus: String): kursus {
        return KursusApiService.getKursusByID(id_kursus).data
    }

}