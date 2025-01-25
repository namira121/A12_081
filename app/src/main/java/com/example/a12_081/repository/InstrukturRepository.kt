package com.example.a12_081.repository

import com.example.a12_081.model.instruktur
import com.example.a12_081.model.instrukturResponse
import com.example.a12_081.service.instrukturService
import okio.IOException

interface InstrukturRepository{
    suspend fun getInstruktur(): instrukturResponse
    suspend fun insertInstruktur(instruktur: instruktur)
    suspend fun updateInstruktur(id_instruktur:String, instruktur: instruktur)
    suspend fun deleteInstruktur(id_instruktur: String)
    suspend fun getInstrukturByID(id_instruktur: String): instruktur
}

class NetworkInstrukturRepository(
    private val InstrukturApiService: instrukturService
):InstrukturRepository{
    override suspend fun getInstruktur(): instrukturResponse {
        return InstrukturApiService.getInstruktur()
    }

    override suspend fun insertInstruktur(instruktur: instruktur) {
        InstrukturApiService.insertInstruktur(instruktur)
    }

    override suspend fun updateInstruktur(id_instruktur: String, instruktur: instruktur) {
        InstrukturApiService.updateInstruktur(id_instruktur, instruktur)
    }

    override suspend fun deleteInstruktur(id_instruktur: String) {
        try {
            val response = InstrukturApiService.deleteInstruktur(id_instruktur)
            if (!response.isSuccessful){
                throw IOException("Failed to delete Mahasiswa, HTTP status code: " +
                        "${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getInstrukturByID(id_instruktur: String): instruktur {
        return InstrukturApiService.getInstrukturByID(id_instruktur).data
    }

}