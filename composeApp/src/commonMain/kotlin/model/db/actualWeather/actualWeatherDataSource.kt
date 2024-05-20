package model.db.actualWeather

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.db.ActualWeather
import com.db.WeatherAppDatabaseKCMP
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class actualWeatherDataSource(db: WeatherAppDatabaseKCMP): ActualDataSource {


        private val queries = db.actualWeatherQueries
        //    Set id = null to let SQLDelight autogenerate the id
        override suspend fun insert(id: Long, latitude: Double, longitude: Double, temperature: Double, humidity: Long, code: Long, relativeT: Double, precipitation: Long) {
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

        override suspend fun getAll(): Flow<List<ActualWeather>> {
            return queries.getAll().asFlow().mapToList(Dispatchers.IO)
        }

}