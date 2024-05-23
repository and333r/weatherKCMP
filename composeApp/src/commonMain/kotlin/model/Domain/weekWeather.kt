package model.Domain

import kotlinx.serialization.Serializable

//TODO: Eliminar clase si no se usa

@Serializable
data class weekWeather (
    val latitude: Double,
    val longitude: Double,
    val hourly: Parameters
)