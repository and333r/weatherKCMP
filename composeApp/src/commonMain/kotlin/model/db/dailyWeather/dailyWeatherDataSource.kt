package model.db.dailyWeather

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.db.ActualWeather
import com.db.DailyWeather
import com.db.WeatherAppDatabaseKCMP
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class dailyWeatherDataSource(db: WeatherAppDatabaseKCMP): DailyDataSource {


    private val queries = db.dailyWeatherQueries
    //    Set id = null to let SQLDelight autogenerate the id
    override suspend fun insert(id: Long, date: String, latitude: Double, longitude: Double, temperature: Double, code: Long) {
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

    override suspend fun getAll(): Flow<List<DailyWeather>> {
        return queries.getAll().asFlow().mapToList(Dispatchers.IO)
    }

}