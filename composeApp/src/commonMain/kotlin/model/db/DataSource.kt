package model.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.db.WeatherAppDatabaseKCMP

class DataSource(private val db: WeatherAppDatabaseKCMP) {

        private val queries = db.historicalWeatherQueries

        //    Set id = null to let SQLDelight autogenerate the id
        fun insert(date: String, latitude: Double, longitude: Double, temperature: Double) {
            queries.insert(
                date = date,
                latitude = latitude,
                longitude = longitude,
                temperature = temperature
            )
        }

        fun getAll() {
            queries.getAll()
        }

        fun getSpecificPlace(latitude: Double, longitude: Double) {
            queries.getSpecificPlace(latitude = latitude, longitude = longitude)

        }


}