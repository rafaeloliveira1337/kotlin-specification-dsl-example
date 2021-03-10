package br.com.rafaelmo.kotlinspecificationdslexample.exception

class InvalidAgeRangeException(
    override val message: String?
) : RuntimeException()