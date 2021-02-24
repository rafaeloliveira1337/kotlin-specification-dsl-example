package br.com.rafaelmo.kotlinspecificationdslexample.clientmodel

import br.com.rafaelmo.kotlinspecificationdslexample.datatransferobject.CustomerDTO

data class CustomerResponse(
    val totalElements: Long,
    val totalPages: Int,
    val customers: List<CustomerDTO>
)