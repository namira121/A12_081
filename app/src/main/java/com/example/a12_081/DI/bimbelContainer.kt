package com.example.a12_081.DI


import com.example.a12_081.repository.InstrukturRepository
import com.example.a12_081.repository.KursusRepository
import com.example.a12_081.repository.NetworkInstrukturRepository
import com.example.a12_081.repository.NetworkKursusRepository
import com.example.a12_081.repository.NetworkPendaftaranRepository
import com.example.a12_081.repository.NetworkSiswaRepository
import com.example.a12_081.repository.PendaftaranRepository
import com.example.a12_081.repository.SiswaRepository
import com.example.a12_081.service.InstrukturService
import com.example.a12_081.service.KursusService
import com.example.a12_081.service.PendaftaranService
import com.example.a12_081.service.SiswaService
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

class BimbelContainer : AppContainer {
    private val baseUrlSiswa = "http://10.0.2.2:3000/api/siswa/"
    private val baseUrlInstruktur = "http://10.0.2.2:3000/api/instruktur/"
    private val baseUrlKursus = "http://10.0.2.2:3000/api/kursus/"
    private val baseUrlPendaftaran = "http://10.0.2.2:3000/api/pendaftaran/"
    private val json = Json { ignoreUnknownKeys = true }

    //siswa
    private val retrofitSiswa: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlSiswa)
        .build()
    private val siswaService: SiswaService by lazy {
        retrofitSiswa.create(SiswaService::class.java)
    }
    override val siswaRepository: SiswaRepository by lazy {
        NetworkSiswaRepository(siswaService)
    }

    //instruktur
    private val retrofitInstruktur: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlInstruktur)
        .build()
    private val instrukturService: InstrukturService by lazy {
        retrofitInstruktur.create(InstrukturService::class.java)
    }
    override val instrukturRepository: InstrukturRepository by lazy {
        NetworkInstrukturRepository(instrukturService)
    }


    //kursus
    private val retrofitKursus: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlKursus)
        .build()
    private val kursusService: KursusService by lazy {
        retrofitKursus.create(KursusService::class.java)
    }
    override val kursusRepository: KursusRepository by lazy {
        NetworkKursusRepository(kursusService)
    }

    //pendaftaran
    private val retrofitPendaftaran: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlPendaftaran)
        .build()
    private val pendaftaranService: PendaftaranService by lazy {
        retrofitPendaftaran.create(PendaftaranService::class.java)
    }
    override val pendaftaranRepository: PendaftaranRepository by lazy {
        NetworkPendaftaranRepository(pendaftaranService)
    }
}
