package ru.easycode.words504.rsrc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val handleResult = HandleResult.Base()
    val primeResult = listOf(
        "zero",
        "one",
        "two",
        "three",
        "four",
        "",
        "",
        "eight",
        "nine",
        "",
        "eleven",
        "twelve",
        "",
        "fourteen",
        "fifteen"
    )
    handleResult.handle(primeResult)

    val secondaryResult = listOf("five", "six", "thirteen", "")
    handleResult.handle(secondaryResult)

    val lastResult = listOf("ten")
    handleResult.handle(lastResult)
}

interface HandleResult {
    fun handle(translateList: List<String>)

    class Base : HandleResult {

        @Volatile
        private var count = 0

        override fun handle(translateList: List<String>) {
            count = 0
            runBlocking {
                translateList.map { word ->
                    CoroutineScope(Dispatchers.IO).launch {
                        if (word.isNotEmpty()) count++
                        val translated = translateWord(word)
                        println(translated)
                    }
                }.joinAll()
                if (count == translateList.size) println("Success!") else println("not success")
                println("------end-of-request------")
            }
        }
    }
}

suspend fun translateWord(word: String):String {
    delay(2000)
    return word + " " + word.isEmpty()
}
