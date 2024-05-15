package model.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.db.WeatherAppDatabaseKCMP

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver =
        NativeSqliteDriver(WeatherAppDatabaseKCMP.Schema, "WeatherAppDatabaseKCMP")
}