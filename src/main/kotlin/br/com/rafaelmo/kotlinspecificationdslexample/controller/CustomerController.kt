package br.com.rafaelmo.kotlinspecificationdslexample.controller

import br.com.rafaelmo.kotlinspecificationdslexample.datatransferobject.CustomerDTO
import br.com.rafaelmo.kotlinspecificationdslexample.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @GetMapping
    fun getCustomers(
        @RequestParam(value = "page", defaultValue = "0", required = false) page: Int,
        @RequestParam(value = "pageSize", defaultValue = "50", required = false) pageSize: Int,
    ): List<CustomerDTO> {
        return customerService.getCustomers(page, pageSize)
    }
}

