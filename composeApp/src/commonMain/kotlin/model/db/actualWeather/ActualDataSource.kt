package model.db.actualWeather

import com.db.ActualWeather
import kotlinx.coroutines.flow.Flow

interface ActualDataSource {
    suspend fun getAll(): Flow<List<ActualWeather>>
    suspend fun insert(id: Long, latitude: Double, longitude: Double, temperature: Double, humidity: Long, code: Long, relativeT: Double, precipitation: Long)
}