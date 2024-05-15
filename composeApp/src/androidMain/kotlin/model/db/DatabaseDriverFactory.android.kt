package model.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.db.WeatherAppDatabaseKCMP

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun create(): SqlDriver =
        AndroidSqliteDriver(WeatherAppDatabaseKCMP.Schema, context, "WeatherAppDatabaseKCMP")
}