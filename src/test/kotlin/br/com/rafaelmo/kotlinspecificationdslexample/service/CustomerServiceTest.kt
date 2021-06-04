package br.com.rafaelmo.kotlinspecificationdslexample.service

import br.com.rafaelmo.kotlinspecificationdslexample.domainobject.Customer
import br.com.rafaelmo.kotlinspecificationdslexample.queryobject.CustomerQueryObject
import br.com.rafaelmo.kotlinspecificationdslexample.repository.CustomerRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

@ExtendWith(MockitoExtension::class)
class CustomerServiceTest {

    @Mock
    private lateinit var customerRepository: CustomerRepository

    @InjectMocks
    private lateinit var customerService: CustomerService

    @Test
    fun `should fetch customer data from repository`() {
        // GIVEN
        val page = 0
        val pageSize = 50
        val customers = listOf(
            Customer(id = 1, firstName = "John", lastName = "Doe", age = 32, car = null)
        )
        val pageResult = PageImpl(customers)
        val queryObject = CustomerQueryObject()
        val pageable = PageRequest.of(page, pageSize)

        given(customerRepository.findAll(any<Specification<Customer>>(), any<Pageable>())).willReturn(pageResult)

        // WHEN
        val fetchedCustomers = customerService.getCustomers(customerQueryObject = queryObject, pageable = pageable)

        // THEN
        assertEquals(1, fetchedCustomers.customers.size)
    }

    @Test
    fun `should fetch customer data filtering by last name`() {
        // GIVEN
        val page = 0
        val pageSize = 50
        val customers = listOf(
            Customer(id = 1, firstName = "John", lastName = "Doe", age = 32, car = null),
            Customer(id = 2, firstName = "Marybeth", lastName = "Williams", age = 32, car = null),
            Customer(id = 3, firstName = "Edward", lastName = "Williams", age = 32, car = null)
        )
        val customersNamedWilliams = customers.filter { it.lastName == "Williams"}

        val pageResult = PageImpl(customersNamedWilliams)
        val queryObject = CustomerQueryObject(lastName = "Williams")
        val pageable = PageRequest.of(page, pageSize)

        given(customerRepository.findAll(any<Specification<Customer>>(), any<Pageable>())).willReturn(pageResult)

        // WHEN
        val fetchedCustomers = customerService.getCustomers(customerQueryObject = queryObject, pageable = pageable)

        // THEN
        assertEquals(2, fetchedCustomers.customers.size)
    }
}