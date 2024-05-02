package ui.dailyWeather

import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import model.data.weatherBL
import kotlin.math.roundToInt


class DailyWeatherViewModel {

    private val initListD = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0 ,11.0, 12.0, 13.0)
    private val initListI = listOf(1, 2, 3, 2, 3, 1, 3, 3, 3, 23, 1, 2, 3)
    private val initListS = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13")

    private val _latitude = MutableStateFlow<String>("43.5667")
    val latitude : StateFlow<String> = _latitude

    private val _longitude = MutableStateFlow<String>("-5.9")
    val longitude : StateFlow<String> = _longitude

    private val _temperatures = MutableStateFlow<List<Double>>(initListD)
    val temperatures : StateFlow<List<Double>> = _temperatures

    private val _codes = MutableStateFlow<List<Int>>(initListI)
    val codes : StateFlow<List<Int>> = _codes

    private val _hours = MutableStateFlow<List<String>>(initListS)
    val hours : StateFlow<List<String>> = _hours


    suspend fun getAllData(latitude: Double, longitude: Double) {
        val weekW = weatherBL.getAllData(latitude, longitude)
        val dayW = weatherBL.getDailyWeather(weekW)
        val currentHour = Clock.System.now()
        var currentTime = currentHour.toLocalDateTime(TimeZone.UTC).hour
        currentTime += 2
        val range = currentTime..23
        val my_array = range.toList().toTypedArray()
        val final_array: MutableList<String> = my_array.map { it.toString() }.toMutableList()
        val test1 = final_array
        val test2 = dayW.temperatures.drop(currentTime)
        val test3 = dayW.codes.drop(currentTime)
        _hours.value = final_array
        _temperatures.value = dayW.temperatures.drop(currentTime)
        _codes.value = dayW.codes.drop(currentTime)
        println(test1)
        println(test2)
        println(test3)


    }

    fun setLatAndLong(latitude: Double, longitude: Double){
        _latitude.value = latitude.toString()
        _longitude.value = longitude.toString()
    }
}