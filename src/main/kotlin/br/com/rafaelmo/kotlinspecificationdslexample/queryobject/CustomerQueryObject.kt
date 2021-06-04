package br.com.rafaelmo.kotlinspecificationdslexample.queryobject

import au.com.console.jpaspecificationdsl.and
import br.com.rafaelmo.kotlinspecificationdslexample.domainobject.Customer
import br.com.rafaelmo.kotlinspecificationdslexample.specification.hasAgeIn
import br.com.rafaelmo.kotlinspecificationdslexample.specification.hasCar
import br.com.rafaelmo.kotlinspecificationdslexample.specification.hasCarFromManufacturer
import br.com.rafaelmo.kotlinspecificationdslexample.specification.hasCarModel
import br.com.rafaelmo.kotlinspecificationdslexample.specification.hasLastName
import org.springframework.data.jpa.domain.Specification

data class CustomerQueryObject(
    val lastName: String? = null,
    val carModel: String? = null,
    val carManufacturer: String? = null,
    val ageRange: List<Int>? = emptyList(),
    val hasCar: Boolean? = null,
)

fun CustomerQueryObject.toSpecification(): Specification<Customer> = and(
    hasLastName(lastName),
    hasCarModel(carModel),
    hasCarFromManufacturer(carManufacturer),
    hasAgeIn(ageRange),
    hasCar(hasCar)
)