package org.example

fun MutableList<Word>.showStatistics() {

    val mutableList = this.filter {
        it.correctAnswersCount >= MAX_CORRECT_ANSWER_COUNT
    }

    val allWords = this.size
    val count = mutableList.size
    val percent = ((count.toDouble() / allWords) * 100).toInt()

    println("Выучено $count из $allWords слов | $percent%")
}