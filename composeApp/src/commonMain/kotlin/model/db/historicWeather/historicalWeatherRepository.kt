package model.db.historicWeather

import com.db.HistoricalWeather
import kotlinx.coroutines.flow.Flow
import model.Domain.historicalWeather

interface historicalWeatherRepository {
    fun getSpecificPlace(latitude: Double, longitude: Double): Flow<Result<List<historicalWeather>>>
    fun getAll():  Flow<Result<List<historicalWeather>>>
    suspend fun insert(date: String, latitude: Double, longitude: Double, temperature: Double)
}