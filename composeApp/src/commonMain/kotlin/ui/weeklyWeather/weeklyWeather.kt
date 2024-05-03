package ui.weeklyWeather

import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import getTextSizeMedium
import getTextSizeSmall
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import weatherappkcmp.composeapp.generated.resources.Res
import weatherappkcmp.composeapp.generated.resources.*
import kotlin.math.roundToInt


@Preview
@Composable
fun weeklyWeather(weeklyWeatherViewModel: WeeklyWeatherViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(520.dp).padding(16.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        val initListMaxMin = listOf(
            Pair(Triple("Sábado", "14", "9.3"), 0),
            Pair(Triple("Domingo", "14", "9.3"), 0),
            Pair(Triple("Lunes", "14", "9.3"), 0),
            Pair(Triple("Martes", "14", "9.3"), 0),
            Pair(Triple("Miercoles", "14", "9.3"), 0),
            Pair(Triple("Jueves", "14", "9.3"), 0),
            Pair(Triple("Viernes", "14", "9.3"), 0)
        )

        val latitude : String by weeklyWeatherViewModel.latitude.collectAsState(initial = "43.5667")
        val longitude : String by weeklyWeatherViewModel.longitude.collectAsState(initial = "-5.9")

        LaunchedEffect(latitude) {
            println("Hola")
            weeklyWeatherViewModel.getAllData(latitude.toDouble(), longitude.toDouble())
            println(latitude)
            println(longitude)
        }

        val maxMin : List<Pair<Triple<String, String, String>, Int>> by weeklyWeatherViewModel.maxMin.collectAsState(initial = initListMaxMin)

        Column(modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(6.dp)).padding(6.dp)) {
            Text(
                text = "Previsión (7 días):",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = getTextSizeSmall().sp
                )
            )
            repeat(maxMin.size) {
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    ) {
                        val imagePainter = returnImagePainter(maxMin[it].second)
                        Box(
                            modifier = Modifier
                                .weight(1.7f)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = maxMin[it].first.first,
                                style = TextStyle(
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = getTextSizeMedium().sp
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(0.75f)
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = imagePainter,
                                contentDescription = "Descripción de la imagen",
                                modifier = Modifier.scale(1F),
                            )

                        }
                        Box(
                            modifier = Modifier
                                .weight(1.5f)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "max: " + maxMin[it].first.second + "º",
                                style = TextStyle(
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = getTextSizeMedium().sp
                                )
                            )

                        }
                        Box(
                            modifier = Modifier
                                .weight(1.4f)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "min: " + maxMin[it].first.third + "º",
                                style = TextStyle(
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = getTextSizeMedium().sp
                                )
                            )
                        }


                    }

                }
            }
        }

    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun returnImagePainter(code: Int): Painter {
    val indexSunny = listOf(0, 51, 53, 55, 56, 57)
    val indexRain = listOf(61, 63, 65, 80, 81, 82)
    val indexStorm = listOf(95, 96, 99)
    val indexSnow = listOf(71, 73, 75, 77, 85, 86)
    val indexCloudyWithSun = listOf(1, 2)
    val indexCloudy = listOf(3, 45, 48)

    return if (indexSunny.contains(code)) {
        painterResource(resource = Res.drawable.soleado)
    } else if (indexRain.contains(code)) {
        painterResource(resource = Res.drawable.lluvia)
    } else if (indexStorm.contains(code)) {
        painterResource(resource = Res.drawable.tormenta)
    } else if (indexSnow.contains(code)) {
        painterResource(resource = Res.drawable.nieve)
    } else if (indexCloudyWithSun.contains(code)) {
        painterResource(resource = Res.drawable.nublado_con_sol)
    } else if (indexCloudy.contains(code)) {
        painterResource(resource = Res.drawable.nublado)
    } else {
        painterResource(resource = Res.drawable.de_noche)
    }
}

@Composable
private fun GradientBackgroundBrush(isVerticalGradient: Boolean, colors: List<Color>): Brush {
    val endOffSet = if (isVerticalGradient) {
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }
    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = endOffSet
    )
}