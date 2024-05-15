package model.Domain

data class historicalWeather (
    val date: String,
    val latitude: Double,
    val longitude: Double,
    val temperature: Double
)