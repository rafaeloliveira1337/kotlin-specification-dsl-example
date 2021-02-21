package br.com.rafaelmo.kotlinspecificationdslexample.service

import br.com.rafaelmo.kotlinspecificationdslexample.domainobject.Customer
import br.com.rafaelmo.kotlinspecificationdslexample.repository.CustomerRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import org.hamcrest.Matchers.any
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

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
            Customer(id = 1, firstName = "John", lastName = "Doe", car = null)
        )
        val pageResult = PageImpl(customers)

        given(customerRepository.findAll(any<Pageable>())).willReturn(pageResult)

        // WHEN
        val fetchedCustomers = customerService.getCustomers(page, pageSize)

        // THEN
        assertEquals(1, fetchedCustomers.size)
    }
}