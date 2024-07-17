package org.example

import java.io.File

fun main() {

    val wordsFile: File = File("words.txt")
    wordsFile.createNewFile()
    wordsFile.writeText("hello|привет\n")
    wordsFile.appendText("dog|собака\n")
    wordsFile.appendText("cat|кошка\n")
    wordsFile.appendText("good morning|доброе утро\n")

    val lines: MutableList<String> = wordsFile.readLines().toMutableList()
    for (line in lines) {
        val line = line.split("|")

        val word = Word(englishText = line[0], russianText = line[1])
        println(word)
    }

    while(true){
        println("\nМеню:")
        when(readln().toInt()){
            1 -> println("Учить слова")
            2 -> {
                print("Статистика: ")
                wordsFile.filter()
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

fun File.filter() {

    val allWords = 6
    val count = 4
    val percent = ((count.toDouble() / allWords) * 100).toInt()

    println("Выучено $count из $allWords слов | $percent%")

}