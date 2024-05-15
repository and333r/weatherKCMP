package model.db

import com.db.HistoricalWeather
import kotlinx.coroutines.flow.Flow
import model.Domain.historicalWeather
import kotlinx.coroutines.flow.StateFlow

interface HistoricDataSource {
    suspend fun getSpecificPlace(latitude: Double, longitude: Double): Flow<List<HistoricalWeather>>
    suspend fun getAll(): Flow<List<HistoricalWeather>>
    suspend fun insert(date: String, latitude: Double, longitude: Double, temperature: Double)
}