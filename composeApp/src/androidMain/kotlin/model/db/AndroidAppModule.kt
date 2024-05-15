package model.db

import android.content.Context
import com.db.WeatherAppDatabaseKCMP

 class AndroidAppModule(private val context: Context, ): AppModule {
    private val db by lazy {
        WeatherAppDatabaseKCMP(
            driver = DatabaseDriverFactory(context).create()
        )
    }

    override fun provideExampleDataSource() = DataSource(db)
}
