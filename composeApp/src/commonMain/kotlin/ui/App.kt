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
fun App(latitude: Double, longitude: Double) {
    MaterialTheme {
        Column {
            val actualWeatherViewModel: ActualWeatherViewModel = ActualWeatherViewModel()
            val dailyWeatherViewModel: DailyWeatherViewModel = DailyWeatherViewModel()
            val weeklyWeatherViewModel: WeeklyWeatherViewModel = WeeklyWeatherViewModel()
            actualWeatherViewModel.setLatAndLong(latitude, longitude)
            dailyWeatherViewModel.setLatAndLong(latitude, longitude)
            weeklyWeatherViewModel.setLatAndLong(latitude, longitude)
            actualWeather(actualWeatherViewModel)
            dailyWeather(dailyWeatherViewModel)
            weeklyWeather(weeklyWeatherViewModel)
        }
    }
}






