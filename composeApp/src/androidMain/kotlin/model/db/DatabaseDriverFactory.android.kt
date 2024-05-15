package model.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.db.WeatherAppDatabaseKCMP

actual class DatabaseDriverFactory actual constructor() {
    actual fun create(): SqlDriver {
       return AndroidSqliteDriver(WeatherAppDatabaseKCMP.Schema, AppContextWrapper.appContext!!, "WeatherAppDatabaseKCMP")
    }
}