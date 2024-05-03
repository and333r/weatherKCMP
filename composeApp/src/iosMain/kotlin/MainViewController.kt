import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import ui.App
import ui.actualWeather.ActualWeatherViewModel
import ui.dailyWeather.DailyWeatherViewModel
import ui.weeklyWeather.WeeklyWeatherViewModel

fun MainViewController(actualWeatherViewModel: ActualWeatherViewModel,
                       dailyWeatherViewModel: DailyWeatherViewModel,
                       weeklyWeatherViewModel: WeeklyWeatherViewModel) = ComposeUIViewController { App(actualWeatherViewModel, dailyWeatherViewModel, weeklyWeatherViewModel) }
