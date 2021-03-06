package br.com.rafaelmo.kotlinspecificationdslexample.service

import au.com.console.jpaspecificationdsl.equal
import br.com.rafaelmo.kotlinspecificationdslexample.clientmodel.CustomerResponse
import br.com.rafaelmo.kotlinspecificationdslexample.domainobject.Customer
import br.com.rafaelmo.kotlinspecificationdslexample.mapper.toCustomerDTO
import br.com.rafaelmo.kotlinspecificationdslexample.queryobject.CustomerQueryObject
import br.com.rafaelmo.kotlinspecificationdslexample.queryobject.toSpecification
import br.com.rafaelmo.kotlinspecificationdslexample.repository.CustomerRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {

    fun getCustomers(customerQueryObject: CustomerQueryObject, pageable: Pageable): CustomerResponse {
        val customers = customerRepository.findAll(customerQueryObject.toSpecification(), pageable)
            .map(Customer::toCustomerDTO)

        return CustomerResponse(
            totalElements = customers.totalElements,
            totalPages = customers.totalPages,
            customers = customers.content
        )
    }

}