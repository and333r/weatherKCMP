package model.db.actualWeather

import kotlinx.coroutines.flow.flow
import model.Domain.actualWeather
class actualWeatherRepositorySQL(
    private val dataSource: ActualDataSource
): actualWeatherRepository {

    override fun getAll() = flow<Result<List<actualWeather>>>{
        dataSource.getAll()
            .collect{i -> emit(Result.success(i.map { j -> j.toActualWeather() }))}
    }

    override suspend fun insert(id: Long, latitude: Double, longitude: Double, temperature: Double, humidity: Long, code: Long, relativeT: Double, precipitation: Long) {
       dataSource.insert(
           id = id,
           longitude = longitude,
           latitude = latitude,
           temperature = temperature,
           humidity = humidity,
           code = code,
           relativeT = relativeT,
           precipitation = precipitation)
    }
}