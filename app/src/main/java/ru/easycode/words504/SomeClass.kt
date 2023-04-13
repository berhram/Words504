package ru.easycode.words504

import java.util.concurrent.atomic.AtomicBoolean
import javax.sql.DataSource

//Dependency
class Person1(val id: String) {

    val isAdult by lazy { AtomicBoolean() }
}

class Person2(val id: String) {

    val isAdult = AtomicBoolean()
}
//Encapsulation

class Repository2(internal val dataSource: DataSource) {

    var page: Int = 0
}
//Functions
interface Person3 {

    fun hello()

    fun hello2()

    fun hello3()

    fun hello4()

    fun hello5()

    fun hello6()
}

interface Person4 {

    fun hello(a: String, b: String, c: String, d: String, e: String, f: String)
}

class Person5 {

    fun hello(a: String, b: String) {}
}

//Inheritance
abstract class AbstractRepository

abstract class AnotherAR : AbstractRepository()

class Repository

//OOP classes
open class Repository3

enum class Tipi {
    TIP1, TIP2
}

sealed class Repository4
