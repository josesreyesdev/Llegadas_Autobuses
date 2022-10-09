package com.devjsr.llegadasautobuses.ui

import android.app.Application
import com.devjsr.llegadasautobuses.database.AppDatabase

class BusScheduleApplication : Application() {

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}