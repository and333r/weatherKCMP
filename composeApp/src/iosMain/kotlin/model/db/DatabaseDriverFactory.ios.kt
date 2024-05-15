package model.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.db.WeatherAppDatabaseKCMP

actual class DatabaseDriverFactory actual constructor() {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(WeatherAppDatabaseKCMP.Schema, "WeatherAppDatabaseKCMP")
    }
}