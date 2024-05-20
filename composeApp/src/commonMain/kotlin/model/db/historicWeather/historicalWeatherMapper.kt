package model.db.historicWeather

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