package model.db.weeklyWeather

import kotlinx.coroutines.flow.Flow
import model.Domain.daySpecWeather
import model.Domain.hourlyWeather

interface weeklyWeatherRepository {
    fun getAll():  Flow<Result<List<daySpecWeather>>>
    suspend fun insert(id: Long, date: String, latitude: Double, longitude: Double, temperatureMax: Double, temperatureMin: Double, code: Long)
}