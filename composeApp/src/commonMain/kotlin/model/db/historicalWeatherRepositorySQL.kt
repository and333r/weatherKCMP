package model.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import model.Domain.historicalWeather

class historicalWeatherRepositorySQL(
    private val dataSource: DataSource
): historicalWeatherRepository {
    override fun getSpecificPlace(
        latitude: Double,
        longitude: Double
    ) = flow<Result<List<historicalWeather>>>{
        dataSource
            .getSpecificPlace(latitude = latitude, longitude = longitude)
            .collect{i -> emit(Result.success(i.map { j -> j.toHistoricalWeather() }))}
    }

    override fun getAll() = flow<Result<List<historicalWeather>>>{
        dataSource.getAll()
            .collect{i -> emit(Result.success(i.map { j -> j.toHistoricalWeather() }))}
    }

    override suspend fun insert(date: String, latitude: Double, longitude: Double, temperature: Double) {
       dataSource.insert(date, latitude, longitude, temperature)
    }
}