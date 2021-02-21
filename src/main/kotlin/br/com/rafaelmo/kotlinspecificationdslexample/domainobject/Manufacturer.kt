package br.com.rafaelmo.kotlinspecificationdslexample.domainobject

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Manufacturer(

    @Id
    @Column
    @GeneratedValue
    val id: Int,

    @Column
    val name: String,

)