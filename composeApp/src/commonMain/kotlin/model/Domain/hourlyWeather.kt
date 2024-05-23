package model.Domain

data class hourlyWeather (
    val date: String,
    val latitude: Double,
    val longitude: Double,
    val temperature: Double,
    val code: Int
)