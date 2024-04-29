package ui.actualWeather

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

    fun returnGradient(code: Int) {
        val indexSunny = listOf(0, 51, 53, 55, 56, 57)
        val gradientColorListSunny = listOf(
            Color(0xFFFFF176),
            Color(0xFFFFEE58),
            Color(0xFFFFEB3B),
            Color(0xFFFFD600),
            Color(0xFFFFC107),

            )
        val gradientColorListNight = listOf(
            Color(0xFF000000),
            Color(0xFF212121),
            Color(0xFF424242),
            Color(0xFF616161),
            Color(0xFF9E9E9E)
        )

        val indexRain = listOf(61, 63, 65, 80, 81, 82)
        val gradientColorListCloudy = listOf(
            Color(0xFF1565C0),
            Color(0xFF1976D2),
            Color(0xFF1E88E5),
            Color(0xFF2196F3),
            Color(0xFF64B5F6)
        )
        val indexStorm = listOf(95, 96, 99)
        val gradientColorListStorm = listOf(
            Color(0xFF212121),
            Color(0xFF455A64),
            Color(0xFF607D8B),
            Color(0xFF78909C),
            Color(0xFF90A4AE)
        )
        val indexSnow = listOf(71, 73, 75, 77, 85, 86)
        val gradientColorListSnow = listOf(
            Color(0xFFE0E0E0),
            Color(0xFFBDBDBD),
            Color(0xFF9E9E9E),
            Color(0xFF757575),
            Color(0xFF424242)
        )
        val indexCloudyWithSun = listOf(1, 2)
        val gradientColorListCloudyWithSun = listOf(
            Color(0xFF81D4FA),
            Color(0xFF4FC3F7),
            Color(0xFF29B6F6),
            Color(0xFF03A9F4),
            Color(0xFF039BE5)
        )
        val indexCloudy = listOf(3, 45, 48)
        val gradientColorListRain = listOf(
            Color(0xFFB0BEC5),
            Color(0xFF90A4AE),
            Color(0xFF78909C),
            Color(0xFF607D8B),
            Color(0xFF455A64)
        )
        if (indexSunny.contains(code)) {
            _gradientColorList.value = gradientColorListSunny
        } else if (indexRain.contains(code)) {
            _gradientColorList.value = gradientColorListRain
        } else if (indexStorm.contains(code)) {
            _gradientColorList.value = gradientColorListStorm
        } else if (indexSnow.contains(code)) {
            _gradientColorList.value = gradientColorListSnow
        } else if (indexCloudyWithSun.contains(code)) {
            _gradientColorList.value = gradientColorListCloudyWithSun
        } else if (indexCloudy.contains(code)) {
            _gradientColorList.value = gradientColorListCloudy
        } else {
            _gradientColorList.value = gradientColorListNight
        }
    }

    fun setLatAndLong(latitude: Double, longitude: Double){
        _latitude.value = latitude.toString()
        _longitude.value = longitude.toString()
    }



}