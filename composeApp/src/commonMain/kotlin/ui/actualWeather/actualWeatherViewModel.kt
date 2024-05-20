package ui.actualWeather

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import model.data.weatherBL
import model.db.DatabaseDriverFactory
import model.db.createDatabase
import model.db.actualWeather.actualWeatherDataSource
import model.db.actualWeather.actualWeatherRepository
import model.db.actualWeather.actualWeatherRepositorySQL
import model.db.historicWeather.historicalWeatherDataSource
import model.db.historicWeather.historicalWeatherRepositorySQL
import kotlin.math.roundToInt


class ActualWeatherViewModel : ViewModel(){
    val color_init = listOf(
        Color(0xFF000000),
        Color(0xFF212121),
        Color(0xFF424242),
        Color(0xFF616161),
        Color(0xFF9E9E9E)
    )

    private val _actualT = MutableStateFlow<String>("14")
    val actualT : StateFlow<String> = _actualT

    private val _actualC = MutableStateFlow<String>("23")
    val actualC : StateFlow<String> = _actualC

    private val _actualH = MutableStateFlow<String>("Humedad: 65%")
    val actualH : StateFlow<String> = _actualH

    private val _actualRT = MutableStateFlow<String>("Sensación térmica: 23ºC")
    val actualRT : StateFlow<String> = _actualRT

    private val _actualP = MutableStateFlow<String>("Precipitaciones: 0%")
    val actualP : StateFlow<String> = _actualP

    private val _estado = MutableStateFlow<String>("Noche")
    val estado : StateFlow<String> = _estado

    private val _gradientColorList = MutableStateFlow<List<Color>>(color_init)
    val gradientColorList : StateFlow<List<Color>> = _gradientColorList

    private val _latitude = MutableStateFlow<String>("43.5667")
    val latitude : StateFlow<String> = _latitude

    private val _longitude = MutableStateFlow<String>("-5.9")
    val longitude : StateFlow<String> = _longitude

    suspend fun getAllData(latitude: Double, longitude: Double) {
        val weekW = weatherBL.getAllData(latitude, longitude)
        val dayW = weatherBL.getDailyWeather(weekW)
        val currentHour = Clock.System.now()
        val currentTime = currentHour.toLocalDateTime(TimeZone.UTC).hour
        val actualWeather = weatherBL.getActualTemperature(dayW, currentTime+1)
        val dayseven = weatherBL.getSpecificWeekDayTemperature(weekW, 6)
        var aux = actualWeather.temperature.roundToInt().toString()
        _gradientColorList.value = weatherBL.returnGradient(actualWeather.code)
        _actualT.value = aux + "º"
        _actualC.value = actualWeather.code.toString()
        aux = actualWeather.humidity.toString()
        _actualH.value = "Humedad: $aux%"
        aux = actualWeather.relativeT.roundToInt().toString()
        _actualRT.value = "Sensación térmica: $aux" + "º"
        aux = actualWeather.precipitation.toString()
        _actualP.value = "Precipitaciones: $aux%"
        _estado.value = weatherBL.returnEstado(_actualC.value!!.toInt())
    }

    fun setLatAndLong(latitude: Double, longitude: Double){
        _latitude.value = latitude.toString()
        _longitude.value = longitude.toString()

    }



}