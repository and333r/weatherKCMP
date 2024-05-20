package model.db.historicWeather

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.db.HistoricalWeather
import com.db.WeatherAppDatabaseKCMP
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class historicalWeatherDataSource(db: WeatherAppDatabaseKCMP): HistoricDataSource {

        private val queries = db.historicalWeatherQueries

        //    Set id = null to let SQLDelight autogenerate the id
        override suspend fun insert(date: String, latitude: Double, longitude: Double, temperature: Double) {
            queries.insert(
                date = date,
                latitude = latitude,
                longitude = longitude,
                temperature = temperature
            )
        }

        override suspend fun getAll(): Flow<List<HistoricalWeather>> {
            return queries.getAll().asFlow().mapToList(Dispatchers.IO)
        }

        override suspend fun getSpecificPlace(latitude: Double, longitude: Double): Flow<List<HistoricalWeather>> {
            return queries.getSpecificPlace(latitude = latitude, longitude = longitude).asFlow().mapToList(Dispatchers.IO)

        }


}