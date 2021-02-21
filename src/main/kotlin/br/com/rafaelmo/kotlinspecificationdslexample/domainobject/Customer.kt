package br.com.rafaelmo.kotlinspecificationdslexample.domainobject

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Entity
class Customer(

    @Id
    @Column
    @GeneratedValue
    val id: Int,

    @Column
    val firstName: String,

    @Column
    val lastName: String,

    @OneToOne
    @JoinColumn(name = "car_id")
    val car: Car?
)