package br.com.rafaelmo.kotlinspecificationdslexample.mapper

import br.com.rafaelmo.kotlinspecificationdslexample.datatransferobject.CarDTO
import br.com.rafaelmo.kotlinspecificationdslexample.datatransferobject.CustomerDTO
import br.com.rafaelmo.kotlinspecificationdslexample.datatransferobject.ManufacturerDTO
import br.com.rafaelmo.kotlinspecificationdslexample.domainobject.Customer

fun Customer.toCustomerDTO(): CustomerDTO {
    return CustomerDTO(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        age = this.age,
        car = this.car?.let { car ->
            CarDTO(
                model = car.model,
                licensePlate = car.licensePlate,
                manufacturer = ManufacturerDTO(
                    name = car.manufacturer.name
                )
            )
        }
    )
}