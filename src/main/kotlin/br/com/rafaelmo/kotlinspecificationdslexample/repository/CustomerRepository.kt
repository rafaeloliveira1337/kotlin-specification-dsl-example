package br.com.rafaelmo.kotlinspecificationdslexample.repository

import br.com.rafaelmo.kotlinspecificationdslexample.domainobject.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Int>