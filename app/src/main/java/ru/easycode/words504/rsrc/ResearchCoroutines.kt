package ru.easycode.words504.rsrc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val handleResult = HandleResult.Base()
    val primeResult = listOf("zero", "one", "two", "three", "four", "", "", "eight", "nine", "", "eleven", "twelve", "", "fourteen")
    handleResult.handle(primeResult)

    val secondaryResult = listOf("five", "six", "thirteen", "")
    handleResult.handle(secondaryResult)

    val lastResult = listOf("ten")
    handleResult.handle(lastResult)
}

interface HandleResult {
    fun handle(list: List<String>)

    class Base : HandleResult {

        @Volatile
        private var count = 0

        override fun handle(list: List<String>) {
            count = 0
            runBlocking {
                list.map { string ->
                    CoroutineScope(Dispatchers.IO).launch {
                        if (string.isNotEmpty()) count++
                        someHeavyOperation(string)
                    }
                }.joinAll()
                if (count == list.size) println("Success!") else println("not success")
                println("------end-of-request------")
            }
        }
    }
}

suspend fun someHeavyOperation(result: String) {
    delay(2000)
    val response = result + " " + result.isEmpty()
    println(response)
}
