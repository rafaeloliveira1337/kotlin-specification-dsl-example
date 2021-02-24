package br.com.rafaelmo.kotlinspecificationdslexample.controller

import br.com.rafaelmo.kotlinspecificationdslexample.queryobject.CustomerQueryObject
import br.com.rafaelmo.kotlinspecificationdslexample.queryobject.toSpecification
import br.com.rafaelmo.kotlinspecificationdslexample.service.CustomerService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(CustomerController::class)
internal class CustomerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var customerService: CustomerService

    @Test
    fun `should return paged customer data`() {
        // GIVEN
        val page = 0
        val pageSize = 50
        val lastName = "Williams"
        val queryObject = CustomerQueryObject(lastName = lastName)

        // WHEN
        val perform = mockMvc.perform(
            get("/customers?page=$page&pageSize=$pageSize&lastName=$lastName")
                .accept(MediaType.APPLICATION_JSON)
        )

        // THEN
        perform.andExpect(status().isOk)
        verify(customerService).getCustomers(customerQueryObject = queryObject, page = page, pageSize = pageSize)
    }
}