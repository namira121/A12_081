package com.example.a12_081.DI

import com.example.a12_081.model.instruktur
import com.example.a12_081.model.kursus
import com.example.a12_081.model.pendaftaran
import com.example.a12_081.repository.InstrukturRepository
import com.example.a12_081.repository.KursusRepository
import com.example.a12_081.repository.NetworkInstrukturRepository
import com.example.a12_081.repository.NetworkKursusRepository
import com.example.a12_081.repository.NetworkPendaftaranRepository
import com.example.a12_081.repository.NetworkSiswaRepository
import com.example.a12_081.repository.PendaftaranRepository
import com.example.a12_081.repository.SiswaRepository
import com.example.a12_081.service.instrukturService
import com.example.a12_081.service.kursusService
import com.example.a12_081.service.pendaftaranService
import com.example.a12_081.service.siswaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val siswaRepository: SiswaRepository
    val instrukturRepository: InstrukturRepository
    val kursusRepository: KursusRepository
    val pendaftaranRepository: PendaftaranRepository
}

class bimbelContainer: AppContainer{
    private val baseUrl = "http://10.0.2.2:3000/api/bimbel/"
    private val json = Json { ignoreUnknownKeys=true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/kson".toMediaType()))
        .baseUrl(baseUrl).build()
    private val siswaService: siswaService by lazy {
        retrofit.create(siswaService::class.java)
    }
    private val instrukturService: instrukturService by lazy {
        retrofit.create(instrukturService::class.java)
    }
    private val kursusService: kursusService by lazy {
        retrofit.create(kursusService::class.java)
    }
    private val pendaftaranService: pendaftaranService by lazy {
        retrofit.create(pendaftaranService::class.java)
    }

    override val siswaRepository: SiswaRepository by lazy {
        NetworkSiswaRepository(siswaService)
    }
    override val instrukturRepository: InstrukturRepository by lazy {
        NetworkInstrukturRepository(instrukturService)
    }
    override val kursusRepository: KursusRepository by lazy{
        NetworkKursusRepository(kursusService)
    }
    override val pendaftaranRepository: PendaftaranRepository by lazy{
        NetworkPendaftaranRepository(pendaftaranService)
    }
}