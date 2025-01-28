package com.example.a12_081

import android.app.Application
import com.example.a12_081.DI.AppContainer
import com.example.a12_081.DI.BimbelContainer

class BimbelApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container=BimbelContainer()
    }
}