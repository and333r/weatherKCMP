package model.db

import app.cash.sqldelight.db.SqlDriver
import com.db.WeatherAppDatabaseKCMP

expect class DatabaseDriverFactory() {
    fun create(): SqlDriver
}

fun createDatabase(driverFactory: DatabaseDriverFactory): WeatherAppDatabaseKCMP{
    return WeatherAppDatabaseKCMP(driverFactory.create())
}