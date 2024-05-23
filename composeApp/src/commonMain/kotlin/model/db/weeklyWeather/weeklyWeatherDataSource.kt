package model.db.weeklyWeather

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.db.WeatherAppDatabaseKCMP
import com.db.WeeklyWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class weeklyWeatherDataSource(db: WeatherAppDatabaseKCMP): WeekDataSource {


    private val queries = db.weeklyWeatherQueries
    //    Set id = null to let SQLDelight autogenerate the id
    override suspend fun insert(id: Long, date: String, latitude: Double, longitude: Double, temperatureMax: Double, temperatureMin: Double, code: Long) {
        queries.insert(
            // id = null,
            //longitude = longitude,
            //  latitude = latitude,
            //  temperature = temperature,
            //humidity = humidity,
            //code = code,
            //relativeT = relativeT,
            //precipitation = precipitation

        )
    }

    override suspend fun getAll(): Flow<List<WeeklyWeather>> {
        return queries.getAll().asFlow().mapToList(Dispatchers.IO)
    }

}