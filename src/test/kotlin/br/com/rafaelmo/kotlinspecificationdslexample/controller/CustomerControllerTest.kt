package br.com.rafaelmo.kotlinspecificationdslexample.controller

import br.com.rafaelmo.kotlinspecificationdslexample.queryobject.CustomerQueryObject
import br.com.rafaelmo.kotlinspecificationdslexample.requestobject.CustomerRequestDTO
import br.com.rafaelmo.kotlinspecificationdslexample.service.CustomerService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageRequest
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

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `should return paged customer data`() {
        // GIVEN
        val page = 0
        val pageSize = 50
        val lastName = "Williams"
        val queryObject = CustomerQueryObject(lastName = lastName, hasCar = true)
        val customerRequestDTO = CustomerRequestDTO(
            lastName = lastName,
            hasCar = true
        )
        val pageable = PageRequest.of(page, pageSize)

        // WHEN
        val perform = mockMvc.perform(
            get("/customers?page=$page&size=$pageSize")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(customerRequestDTO))
        )

        // THEN
        perform.andExpect(status().isOk)
        verify(customerService).getCustomers(customerQueryObject = queryObject, pageable = pageable)
    }
}