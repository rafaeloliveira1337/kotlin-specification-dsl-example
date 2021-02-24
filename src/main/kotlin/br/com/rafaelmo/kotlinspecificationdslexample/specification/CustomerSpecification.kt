package br.com.rafaelmo.kotlinspecificationdslexample.specification

import au.com.console.jpaspecificationdsl.`in`
import au.com.console.jpaspecificationdsl.equal
import au.com.console.jpaspecificationdsl.get
import au.com.console.jpaspecificationdsl.isNotNull
import au.com.console.jpaspecificationdsl.isNull
import au.com.console.jpaspecificationdsl.where
import br.com.rafaelmo.kotlinspecificationdslexample.domainobject.Car
import br.com.rafaelmo.kotlinspecificationdslexample.domainobject.Customer
import br.com.rafaelmo.kotlinspecificationdslexample.domainobject.Manufacturer
import org.springframework.data.jpa.domain.Specification

fun hasLastName(lastName: String?): Specification<Customer>? = lastName?.let {
    Customer::lastName.equal(lastName)
}

fun hasCarModel(carModel: String?): Specification<Customer>? = carModel?.let {
    where {
        equal(it.get(Customer::car).get(Car::model), carModel)
    }
}

fun hasCarFromManufacturer(manufacturer: String?): Specification<Customer>? = manufacturer?.let {
    where {
        equal(it.get(Customer::car).get(Car::manufacturer).get(Manufacturer::name), manufacturer)
    }
}

fun hasAgeIn(ageRange: List<Int>?): Specification<Customer>? = ageRange?.let {
    Customer::age.`in`(ageRange)
}

fun hasCar(hasCar: Boolean?): Specification<Customer>? = hasCar?.let {
    if (hasCar) Customer::car.isNotNull() else Customer::car.isNull()
}