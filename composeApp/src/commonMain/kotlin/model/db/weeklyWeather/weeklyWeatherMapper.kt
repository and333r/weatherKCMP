package model.db.weeklyWeather

import com.db.DailyWeather
import com.db.WeeklyWeather
import model.Domain.daySpecWeather
import model.Domain.hourlyWeather

fun WeeklyWeather.toWeeklyWeather(): daySpecWeather{
    return daySpecWeather(
        date = date,
        latitude = latitude,
        longitude = longitude,
        temperatureMax = temperatureMax,
        temperatureMin = temperatureMin,
        code = code.toInt(),
    )
}