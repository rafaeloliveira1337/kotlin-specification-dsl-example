package br.com.rafaelmo.kotlinspecificationdslexample.datatransferobject

data class CarDTO(
    val model: String,
    val licensePlate: String,
    val manufacturer: ManufacturerDTO
)