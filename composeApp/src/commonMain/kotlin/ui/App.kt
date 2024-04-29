package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import model.data.weatherAPI
import model.data.weatherBL
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.actualWeather.ActualWeatherViewModel
import ui.actualWeather.actualWeather
import ui.dailyWeather.DailyWeatherViewModel
import ui.dailyWeather.dailyWeather
import ui.weeklyWeather.WeeklyWeatherViewModel
import ui.weeklyWeather.weeklyWeather

@Preview
@Composable
fun App() {
    MaterialTheme {
        Column {
            val actualWeatherViewModel: ActualWeatherViewModel = ActualWeatherViewModel()
            val dailyWeatherViewModel: DailyWeatherViewModel = DailyWeatherViewModel()
            val weeklyWeatherViewModel: WeeklyWeatherViewModel = WeeklyWeatherViewModel()

            actualWeather(actualWeatherViewModel)
            dailyWeather(dailyWeatherViewModel)
            weeklyWeather(weeklyWeatherViewModel)
        }
    }
}


@Preview
@Composable
fun preview(){
    App()
}






