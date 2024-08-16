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

        loadDictionary()

        println("\nМеню:")
        when (readln().toInt()) {
            1 -> { println("УЧИТЬ СЛОВА")
                dictionary.learnWords()}
            2 -> { print("СТАТИСТИКА: ")
                dictionary.showStatistics()}

            0 -> break
            else -> println("Внимание! Введите 1, 2 или для выхода нажмите 0.")
        }
    }
}

fun loadDictionary() {
    TODO("Not yet implemented")
}

fun MutableList<Word>.learnWords() {

    val unlearnedWords = this.takeWhile { it.correctAnswersCount < 3 }

    val answers = unlearnedWords.take(4).shuffled()

    println("Выбери правильный вариант перевода: " + answers.random().englishText)
    println(answers.map { it.russianText })

    for (i in 0..answers.size - 1)
        if (answers[i].correctAnswersCount < 3){
            continue
        } else {
            println("Вы выучили все слова!")
            break
        }
}

fun MutableList<Word>.showStatistics() {

    val mutableList = this.filter {
        it.correctAnswersCount >= MAX_CORRECT_ANSWER_COUNT
    }

    println("Выучено ${mutableList.size} из ${this.size} слов | ${((mutableList.size.toDouble() / this.size) * 100).toInt()}%")

}