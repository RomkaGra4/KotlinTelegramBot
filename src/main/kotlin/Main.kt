package org.example

import java.io.File

fun main() {

    val wordsFile: File = File("words.txt")
    wordsFile.createNewFile()
    wordsFile.writeText("hello|привет\n")
    wordsFile.appendText("dog|собака\n")
    wordsFile.appendText("cat|кошка\n")
    wordsFile.appendText("good morning|доброе утро\n")

    val lines: List<String> = wordsFile.readLines()
    for (line in lines) {
        val line = line.split("|")
        val word = Word(englishText = line[0], russianText = line[1])
        println(word)
    }

}

data class Word(
    val englishText: String,
    val russianText: String,
    val correctAnswersCount: Int = 0,
)
