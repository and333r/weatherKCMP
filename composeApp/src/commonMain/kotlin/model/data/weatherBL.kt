package model.data

import androidx.compose.ui.graphics.Color
import io.ktor.client.call.body
import io.ktor.client.request.get
import model.Domain.actualWeather
import model.Domain.dayWeather
import model.Domain.weekWeather
import kotlin.math.roundToInt


class weatherBL {
    companion object {

        suspend fun getAllData(latitude: Double, longitude: Double): weekWeather {
            val URL = "https://api.open-meteo.com/v1/forecast?latitude=$latitude&longitude=$longitude&hourly=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation_probability,weather_code&timezone=Europe%2FBerlin"
            println("Retrieving data...")
            val data = weatherAPI.httpClient.get(URL).body<weekWeather>()
            println("Data retrieved...")
            return data
        }

        fun getDailyWeather(weekWeather: weekWeather): dayWeather {
            println("Retrieving daily data...")
            return dayWeather(weekWeather.latitude, weekWeather.longitude,
                weekWeather.hourly.temperature_2m.take(24),
                weekWeather.hourly.relative_humidity_2m.take(24),
                weekWeather.hourly.weather_code.take(24),
                weekWeather.hourly.apparent_temperature.take(24),
                weekWeather.hourly.precipitation_probability.take(24)
            )
        }

        fun getActualTemperature(dayWeather: dayWeather, hour: Int): actualWeather {
            println("Retrieving actual data...")
            return actualWeather(dayWeather.latitude, dayWeather.longitude,
                dayWeather.temperatures[hour],
                dayWeather.humidities[hour],
                dayWeather.codes[hour],
                dayWeather.relativeTs[hour],
                dayWeather.precipitations[hour]
            )
        }

        fun getSpecificWeekDayTemperature(weekWeather: weekWeather, dayNumber: Int): dayWeather{
            println("Retrieving each days data...")
            return dayWeather(weekWeather.latitude, weekWeather.longitude,
                weekWeather.hourly.temperature_2m.subList((24*(dayNumber-1)), 24*dayNumber),
                weekWeather.hourly.relative_humidity_2m.subList((24*(dayNumber-1)), 24*dayNumber),
                weekWeather.hourly.weather_code.subList((24*(dayNumber-1)), 24*dayNumber),
                weekWeather.hourly.apparent_temperature.subList((24*(dayNumber-1)), 24*dayNumber),
                weekWeather.hourly.precipitation_probability.subList((24*(dayNumber-1)), 24*dayNumber)
            )
        }

        fun returnEstado(code: Int): String {
            val indexSunny = listOf(0, 51, 53, 55, 56, 57)
            val indexRain = listOf(61, 63, 65, 80, 81, 82)
            val indexStorm = listOf(95, 96, 99)
            val indexSnow = listOf(71, 73, 75, 77, 85, 86)
            val indexCloudyWithSun = listOf(1, 2)
            val indexCloudy = listOf(3, 45, 48)

            return if (indexSunny.contains(code)) {
                "Soleado"
            } else if (indexRain.contains(code)) {
                "Lluvioso"
            } else if (indexStorm.contains(code)) {
                "Tormenta"
            } else if (indexSnow.contains(code)) {
                "Nevando"
            } else if (indexCloudyWithSun.contains(code)) {
                "Nuboso"
            } else if (indexCloudy.contains(code)) {
                "Nublado"
            } else {
                "Noche"
            }
        }

        fun returnGradient(code: Int): List<Color> {
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
            return if (indexSunny.contains(code)) {
                gradientColorListSunny
            } else if (indexRain.contains(code)) {
                gradientColorListRain
            } else if (indexStorm.contains(code)) {
                gradientColorListStorm
            } else if (indexSnow.contains(code)) {
                gradientColorListSnow
            } else if (indexCloudyWithSun.contains(code)) {
                gradientColorListCloudyWithSun
            } else if (indexCloudy.contains(code)) {
                gradientColorListCloudy
            } else {
                gradientColorListNight
            }
        }

        fun getMaxAndMinT(dayWeather: dayWeather): Pair<String, String> {
            var max = -1
            var min = 1000
            for (t in dayWeather.temperatures) {
                if (t > max) {
                    max = t.roundToInt()
                } else if (t<min){
                    min = t.roundToInt()
                }
            }
            return Pair(max.toString(), min.toString())
        }

        fun getAverageCode(dayWeather: dayWeather): Int {
            val conteo = dayWeather.codes.groupingBy { it }.eachCount()
            val numeroMasRepetido = conteo.maxByOrNull { it.value }?.key
            print("Numero mas repetido: $numeroMasRepetido")
            return numeroMasRepetido?.toInt() ?: 0
        }


    }

}