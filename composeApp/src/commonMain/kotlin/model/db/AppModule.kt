package model.db

interface AppModule {
    fun provideExampleDataSource(): DataSource
}