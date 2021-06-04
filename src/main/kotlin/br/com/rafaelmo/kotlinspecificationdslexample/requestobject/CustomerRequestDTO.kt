package br.com.rafaelmo.kotlinspecificationdslexample.requestobject

import br.com.rafaelmo.kotlinspecificationdslexample.queryobject.CustomerQueryObject

data class CustomerRequestDTO(
    val lastName: String? = null,
    val carModel: String? = null,
    val carManufacturer: String? = null,
    val minAge: Int? = null,
    val maxAge: Int? = null,
    val hasCar: Boolean? = null
)

fun CustomerRequestDTO.toCustomerQueryObject(ageRange: List<Int>?) = CustomerQueryObject(
    lastName = lastName,
    carModel = carModel,
    carManufacturer = carManufacturer,
    ageRange = ageRange,
    hasCar = hasCar
)