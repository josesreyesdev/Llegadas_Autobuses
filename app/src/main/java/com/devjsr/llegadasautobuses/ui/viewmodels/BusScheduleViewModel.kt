package com.devjsr.llegadasautobuses.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devjsr.llegadasautobuses.database.schedule.Schedule
import com.devjsr.llegadasautobuses.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel( private val scheduleDao: ScheduleDao) : ViewModel() {

    //Horario completo
    fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()

    //Horario fltrado por nombre de parada
    fun scheduleForStopName( name: String) : Flow<List<Schedule>> = scheduleDao.getByStopName(name)

}

class BusScheduleViewModelFactory(private val scheduleDao: ScheduleDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class / no se encontro la clase viewModel")
    }
}