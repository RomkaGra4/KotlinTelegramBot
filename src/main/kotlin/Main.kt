package org.example

val trainer = LearnWordsTrainer("words.txt")

fun Question.asConsoleString(): String{
    val variants = this.variants.mapIndexed { index: Int, word: Word -> " ${index + 1} - ${word.russianText}" }
        .joinToString(separator =  "\n")
    return this.correctAnswer.englishText + "\n" + variants +"\n. 0 - выйти в МЕНЮ"

}

fun main() {


    while (true) {

        println("\nМеню:")
        when (readln().toInt()) {
            1 -> {
                println("УЧИТЬ СЛОВА")
                trainer.dictionary.learnWords()
            }

            2 -> {
                print("СТАТИСТИКА: ")
                trainer.dictionary.showStatistics()
            }

            0 -> break
            else -> println("Внимание! Введите 1, 2 или для выхода нажмите 0.")
        }
    }
}

fun List<Word>.learnWords() {

    while (true) {

        val question = trainer.getNextQuestion()
        if (question == null){
            println("Все слова выучены")
            break
        } else {
            println(question.asConsoleString())
            val userAnswerInput = readln().toIntOrNull()
            if (userAnswerInput == 0) break

            if (trainer.checkAnswer(userAnswerInput?.minus(1))){

                println("Это правильный ответ!\n")
            } else {
                println("Неправильно!")
            }
        }

    }
}
fun List<Word>.showStatistics() {

    val statistics = trainer.getStatistics()
    println("Выучено ${statistics.learned} из ${statistics.total} слов | ${statistics.percent}%")
}