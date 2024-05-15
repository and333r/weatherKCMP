package model.db

import com.db.WeatherAppDatabaseKCMP

class IosAppModule : AppModule {
    private val db by lazy {
        WeatherAppDatabaseKCMP(
            driver = DatabaseDriverFactory().create()
        )
    }

    override fun provideExampleDataSource() = DataSource(db)
}