package br.com.rafaelmo.kotlinspecificationdslexample.service

import br.com.rafaelmo.kotlinspecificationdslexample.datatransferobject.CarDTO
import br.com.rafaelmo.kotlinspecificationdslexample.datatransferobject.CustomerDTO
import br.com.rafaelmo.kotlinspecificationdslexample.datatransferobject.ManufacturerDTO
import br.com.rafaelmo.kotlinspecificationdslexample.repository.CustomerRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {

    fun getCustomers(page: Int, pageSize: Int): List<CustomerDTO> {
        val pageRequest = PageRequest.of(page, pageSize)

        val customers = customerRepository.findAll(pageRequest)
            .map {
                CustomerDTO(
                    id = it.id,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    car = it.car?.let { car ->
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

        return customers.content
    }

}