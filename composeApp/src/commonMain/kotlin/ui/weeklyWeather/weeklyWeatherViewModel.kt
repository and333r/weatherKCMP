package ui.weeklyWeather

import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import model.Domain.dayWeather
import model.data.weatherBL
import kotlin.math.roundToInt

class WeeklyWeatherViewModel {

    val initListMaxMin = listOf(
        Pair(Triple("Sábado", "14", "9.3"), 0),
        Pair(Triple("Domingo", "14", "9.3"), 0),
        Pair(Triple("Lunes", "14", "9.3"), 0),
        Pair(Triple("Martes", "14", "9.3"), 0),
        Pair(Triple("Miercoles", "14", "9.3"), 0),
        Pair(Triple("Jueves", "14", "9.3"), 0),
        Pair(Triple("Viernes", "14", "9.3"), 0)
    )

    private val initListI = listOf(1, 2, 3, 2, 3, 1, 3)
    private val initListS = listOf("1", "2", "3", "4", "5", "6", "7")



    private val _latitude = MutableStateFlow<String>("43.5667")
    val latitude: StateFlow<String> = _latitude

    private val _longitude = MutableStateFlow<String>("-5.9")
    val longitude: StateFlow<String> = _longitude

    //private val _temperatures = MutableStateFlow<List<Double>>()
    //val temperatures: StateFlow<List<Double>> = _temperatures

    private val _maxMin = MutableStateFlow<List<Pair<Triple<String, String, String>, Int>>>(initListMaxMin)
    val maxMin: StateFlow<List<Pair<Triple<String, String, String>, Int>>> = _maxMin

    private val _codes = MutableStateFlow<List<Int>>(initListI)
    val codes: StateFlow<List<Int>> = _codes

    private val _hours = MutableStateFlow<List<String>>(initListS)
    val hours: StateFlow<List<String>> = _hours



    private val weekDayList: List<String> = listOf("Lunes", "Martes", "Miercoles", "Jueves",
        "Viernes", "Sábado", "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes",
        "Sábado")

    suspend fun getAllData(latitude: Double, longitude: Double) {
        val weekW = weatherBL.getAllData(latitude, longitude)
        val dayW = weatherBL.getDailyWeather(weekW)
        val currentHour = Clock.System.now()
        val currentTime = currentHour.toLocalDateTime(TimeZone.UTC).dayOfWeek.ordinal
        var lista = emptyList<Pair<Triple<String, String, String>, Int>>().toMutableList()
        println("Dia de la semana: $currentTime")
        for (i in 1..7) {
            val currentDay = weatherBL.getSpecificWeekDayTemperature(weekW, i)
            val currentMaxMin = weatherBL.getMaxAndMinT(currentDay)
            val mostRepeatedCode = weatherBL.getAverageCode(currentDay)
            val weekDaySpanish = weekDayList.get((currentTime + i)-1)
            val stringFormatted = weekDaySpanish + currentMaxMin
            println(stringFormatted)
            lista.add(Pair(Triple(weekDaySpanish, currentMaxMin.first, currentMaxMin.second), mostRepeatedCode))
        }
        _maxMin.value = lista


    }

    fun setLatAndLong(latitude: Double, longitude: Double) {
        _latitude.value = latitude.toString()
        _longitude.value = longitude.toString()
    }
}