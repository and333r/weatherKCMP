package model.db.dailyWeather

import kotlinx.coroutines.flow.Flow
import model.Domain.hourlyWeather

interface dailyWeatherRepository {
    fun getAll():  Flow<Result<List<hourlyWeather>>>
    suspend fun insert(id: Long, date: String, latitude: Double, longitude: Double, temperature: Double, code: Long)
}