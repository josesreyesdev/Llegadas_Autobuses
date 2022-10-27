package com.devjsr.llegadasautobuses.database.schedule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity  //@Entity( tableName = "schedule") => asignar nombre a la tabla
data class Schedule(
    @PrimaryKey val id: Int,
    @ColumnInfo( name = "stop_name") val stopName: String,
    @ColumnInfo( name = "arrival_time") val arrivalTime: Int
)
