package org.example

import java.io.File

fun main() {

    val wordsFile: File = File("words.txt")
    wordsFile.createNewFile()
    wordsFile.writeText("hello привет\n")
    wordsFile.appendText("dog собака\n")
    wordsFile.appendText("cat кошка\n")

    var i = 0
    while (i < wordsFile.length()) {
        println(wordsFile.readLines()[i])
        i++
    }
}
