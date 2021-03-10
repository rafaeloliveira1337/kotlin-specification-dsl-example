package br.com.rafaelmo.kotlinspecificationdslexample.controller

import br.com.rafaelmo.kotlinspecificationdslexample.clientmodel.CustomerResponse
import br.com.rafaelmo.kotlinspecificationdslexample.exception.InvalidAgeRangeException
import br.com.rafaelmo.kotlinspecificationdslexample.queryobject.CustomerQueryObject
import br.com.rafaelmo.kotlinspecificationdslexample.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @GetMapping
    fun getCustomers(
        @RequestParam(value = "page", defaultValue = "0", required = false) page: Int,
        @RequestParam(value = "pageSize", defaultValue = "50", required = false) pageSize: Int,
        @RequestParam(value = "lastName", required = false) lastName: String?,
        @RequestParam(value = "carModel", required = false) carModel: String?,
        @RequestParam(value = "carManufacturer", required = false) carManufacturer: String?,
        @RequestParam(value = "minAge", required = false) minAge: Int?,
        @RequestParam(value = "maxAge", required = false) maxAge: Int?,
        @RequestParam(value = "hasCar", required = false) hasCar: Boolean?,
    ): CustomerResponse {

        var ageRange: List<Int>? = emptyList()
        if (minAge != null && maxAge != null) {
            if (maxAge < minAge) throw InvalidAgeRangeException("minAge cannot be greater than maxAge")

            ageRange = (minAge..maxAge).toList()
        }

        val customerQueryObject = CustomerQueryObject(
            lastName = lastName,
            carModel = carModel,
            carManufacturer = carManufacturer,
            ageRange = ageRange,
            hasCar = hasCar
        )

        return customerService.getCustomers(customerQueryObject, page, pageSize)
    }
}
