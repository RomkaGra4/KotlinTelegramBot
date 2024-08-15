package org.example

data class Word(
    val englishText: String,
    val russianText: String,
    var correctAnswersCount: Int = 0,
)