package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.actualWeather.ActualWeatherViewModel
import ui.actualWeather.actualWeather
import ui.dailyWeather.DailyWeatherViewModel
import ui.dailyWeather.dailyWeather
import ui.weeklyWeather.WeeklyWeatherViewModel
import ui.weeklyWeather.weeklyWeather

@Preview
@Composable
fun App(actualWeatherViewModel: ActualWeatherViewModel,
        dailyWeatherViewModel: DailyWeatherViewModel,
        weeklyWeatherViewModel: WeeklyWeatherViewModel) {

    MaterialTheme {
        Column {
            actualWeather(actualWeatherViewModel)
            dailyWeather(dailyWeatherViewModel)
            weeklyWeather(weeklyWeatherViewModel)
        }
    }
}




