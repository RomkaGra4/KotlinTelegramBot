package org.example

import java.io.File

const val MAX_CORRECT_ANSWER_COUNT = 3

fun main() {

    val wordsFile: File = File("words.txt")
    wordsFile.createNewFile()

    val dictionary: MutableList<Word> = mutableListOf()
    wordsFile.readLines().map {
        val text = it.split("|")

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
        it.correctAnswersCount >= MAX_CORRECT_ANSWER_COUNT
    }

    val allWords = this.size
    val count = mutableList.size
    val percent = ((count.toDouble() / allWords) * 100).toInt()

    println("Выучено $count из $allWords слов | $percent%")
}