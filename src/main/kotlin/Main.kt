package org.example
import java.io.File

const val MAX_CORRECT_ANSWER_COUNT = 3

fun main() {

    while (true) {

        val dictionary: List<Word> = loadDictionary()

        println("\nМеню:")
        when (readln().toInt()) {
            1 -> {
                println("УЧИТЬ СЛОВА")
                dictionary.learnWords()
            }

            2 -> {
                print("СТАТИСТИКА: ")
                dictionary.showStatistics()
            }

            0 -> break
            else -> println("Внимание! Введите 1, 2 или для выхода нажмите 0.")
        }
    }
}

fun loadDictionary(): List<Word> {
    val wordsFile: File = File("words.txt")
    wordsFile.createNewFile()

    val dictionary: MutableList<Word> = mutableListOf()
    wordsFile.readLines().map {
        val text = it.split("|")

        val word = Word(englishText = text[0], russianText = text[1])
        dictionary.add(word)
    }

    return dictionary
}

fun List<Word>.learnWords() {

    while (true) {

        val unlearnedWords = this.takeWhile { it.correctAnswersCount < 3 }
        val answers = unlearnedWords.take(4).shuffled()
        val variants = this.take(4).shuffled()
        val question = answers.random()

        println("\nВыбери правильный вариант перевода: " + question.englishText)
        println(variants.take(4).map{ it.russianText })

        val userResponse = readln().toInt()

        if (userResponse in 1..4) {

            if (variants[userResponse - 1].englishText == question.englishText) {
                println("Это правильный ответ!")
                answers[userResponse - 1].correctAnswersCount += 1
                saveDictionary(answers)
            } else {
                println("Нет, это неправильный ответ.")
            }


        } else if (userResponse == 0) {
            println("Вы вышли в ГЛАВНОЕ МЕНЮ")
            break
        } else {
            println("Выберите 1 из 4 вариантов ответа, указав правильный по списку!")
        }


        for (element in unlearnedWords) {
            if (element.correctAnswersCount < 3) {
                continue
            } else {
                println("Вы выучили все слова!")
                break
            }
        }
    }
}

fun saveDictionary(answers: List<Word>) {
    val wordsFile: File = File("words.txt")
        wordsFile.writeText(answers.toString())
}

fun List<Word>.showStatistics() {

    val mutableList = this.filter {
        it.correctAnswersCount >= MAX_CORRECT_ANSWER_COUNT
    }

    println("Выучено ${mutableList.size} из ${this.size} слов | ${((mutableList.size.toDouble() / this.size) * 100).toInt()}%")
}