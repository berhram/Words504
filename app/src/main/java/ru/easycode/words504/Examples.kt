package ru.easycode.words504

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.HTTP
import retrofit2.http.OPTIONS
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT

// Encapsulation rule passed

interface DataSource

class Repository1(private val dataSource: DataSource) {
    private var page: Int = 0
}


abstract class Repository2(private val dataSource: DataSource) {

    protected var page: Int = 0
}


abstract class Repository3(protected val dataSource: DataSource) {

    private var page: Int = 0
}


abstract class Repository4(protected val dataSource: DataSource) {

    protected var page: Int = 0
}
//Encapsulation not passed

class Repository5(internal val dataSource: DataSource) {

    var page: Int = 0
}


abstract class Repository6(internal val dataSource: DataSource) {

    protected val page: Int = 0
}


abstract class Repository7(val dataSource: DataSource) {

    private val page: Int = 0
}


abstract class Repository8(protected val dataSource: DataSource) {

    val page: Int = 0
}


abstract class Repository0(private val dataSource: DataSource) {

    val page: Int = 0
}

// Functions rule passed

interface SayHello {

    fun hello(a: String, b: String)
}

class Person1 : SayHello {

    override fun hello(a: String, b: String) {}
}


interface Person2 {

    fun hello()

    fun hello2()

    fun hello3()

    fun hello4()

    fun hello5()
}


interface Person3 {

    fun hello(a: String, b: String, c: String, d: String, e: String)
}

interface Service1 {

    @GET("hello")
    fun hello(a: String, b: String, c: String, d: String, e: String, f: String, g: String)

    @DELETE("hello")
    fun hello2(a: String, b: String, c: String, d: String, e: String, f: String, g: String)

    @POST("hello")
    fun hello3(a: String, b: String, c: String, d: String, e: String, f: String, g: String)

    @PUT("hello")
    fun hello4(a: String, b: String, c: String, d: String, e: String, f: String, g: String)
}

interface Service2 {

    @PATCH("hello")
    fun hello5(a: String, b: String, c: String, d: String, e: String, f: String, g: String)

    @HEAD("hello")
    fun hello6(a: String, b: String, c: String, d: String, e: String, f: String, g: String)

    @OPTIONS("hello")
    fun hello7(a: String, b: String, c: String, d: String, e: String, f: String, g: String)

    @HTTP(method = "hello")
    fun hello8(a: String, b: String, c: String, d: String, e: String, f: String, g: String)
}


// Functions rule not passed


interface Person4 {

    fun hello()

    fun hello2()

    fun hello3()

    fun hello4()

    fun hello5()

    fun hello6()
}


interface Person5 {

    fun hello(a: String, b: String, c: String, d: String, e: String, f: String)
}


class Person6 {

    fun hello(a: String, b: String) {}
}

// Inheritance rule passed

abstract class SomeClass

interface SomeInterface

interface SomeInterface2

class Repository9 : SomeClass(), SomeInterface, SomeInterface2

class Repository10 : SomeInterface

class Repository11 : SomeClass()

// Inheritance rule not passed

class Repository12

abstract class AnotherAR : SomeClass()

// Oop classed rule passed

class Repository13

// Oop classes rule not passed

sealed class Person7 {

    object John : Person7()
}

enum class Person8 {

    JOHN, NICK
}

open class Person9
