package br.com.rafaelmo.kotlinspecificationdslexample.domainobject

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
class Car(

    @Id
    @Column
    @GeneratedValue
    val id: Int,

    @Column
    val model: String,

    @Column
    val licensePlate: String,

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    val manufacturer: Manufacturer
)