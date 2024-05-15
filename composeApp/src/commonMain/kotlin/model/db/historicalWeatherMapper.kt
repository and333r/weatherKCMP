package model.db

import com.db.HistoricalWeather
import model.Domain.historicalWeather

fun HistoricalWeather.toHistoricalWeather(): historicalWeather{
    return historicalWeather(
        date = date,
        latitude = latitude,
        longitude = longitude,
        temperature = temperature
    )
}