package br.com.rafaelmo.kotlinspecificationdslexample.controller

import br.com.rafaelmo.kotlinspecificationdslexample.clientmodel.CustomerResponse
import br.com.rafaelmo.kotlinspecificationdslexample.exception.InvalidAgeRangeException
import br.com.rafaelmo.kotlinspecificationdslexample.requestobject.CustomerRequestDTO
import br.com.rafaelmo.kotlinspecificationdslexample.requestobject.toCustomerQueryObject
import br.com.rafaelmo.kotlinspecificationdslexample.service.CustomerService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @GetMapping
    fun getCustomers(
        @RequestBody customerRequestDTO: CustomerRequestDTO,
        pageable: Pageable
    ): CustomerResponse {

        var ageRange: List<Int>? = emptyList()
        if (customerRequestDTO.minAge != null && customerRequestDTO.maxAge != null) {
            if (customerRequestDTO.maxAge < customerRequestDTO.minAge) throw InvalidAgeRangeException("minAge cannot be greater than maxAge")

            ageRange = (customerRequestDTO.minAge..customerRequestDTO.maxAge).toList()
        }

        return customerService.getCustomers(customerRequestDTO.toCustomerQueryObject(ageRange), pageable)
    }
}