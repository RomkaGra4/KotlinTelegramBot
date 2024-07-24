package org.example

import java.io.File

    const val MAX_COUNT = 3

fun main() {

    val wordsFile: File = File("words.txt")
    wordsFile.createNewFile()
    wordsFile.writeText("hello|привет\n")
    wordsFile.appendText("dog|собака\n")
    wordsFile.appendText("cat|кошка\n")
    wordsFile.appendText("good morning|доброе утро\n")

    val lines: MutableList<String> = wordsFile.readLines().toMutableList()
    val dictionary: MutableList<Word> = mutableListOf()

    for (line in lines) {
        val text = line.split("|")

        val word = Word(englishText = text[0], russianText = text[1])
        dictionary.add(word)

    }
    println(dictionary)

    while (true) {
        println("\nМеню:")
        when (readln().toInt()) {
            1 -> println("Учить слова")
            2 -> {
                print("Статистика: ")
                dictionary.showStatistics()
            }

            0 -> break
            else -> println("Внимание! Введите 1, 2 или для выхода нажмите 0.")
        }
    }
}

data class Word(
    val englishText: String,
    val russianText: String,
    val correctAnswersCount: Int = 0,
)

fun MutableList<Word>.showStatistics() {

    val mutableList = this.filter {
        it.correctAnswersCount >= MAX_COUNT
    }

    val allWords = this.size
    val count = mutableList.size
    val percent = ((count.toDouble() / allWords) * 100).toInt()

    println("Выучено $count из $allWords слов | $percent%")
}