package org.example
import java.io.File

data class Statistics(
    val learned: Int,
    val total: Int,
    val percent: Int,
)

data class Question(
    val variants: List<Word>,
    val correctAnswer : Word,
)


class LearnWordsTrainer {

    private var question: Question? = null
    val dictionary: List<Word> = loadDictionary()

    fun getStatistics(): Statistics {
        val learned = trainer.dictionary.filter { it.correctAnswersCount >= MAX_CORRECT_ANSWER_COUNT }.size
        val total = trainer.dictionary.size
        val percent = learned * 100 / total
        return Statistics(learned, total, percent)

    }

    fun getNextQuestion(): Question? {

        val notLearnedist = dictionary.filter { it.correctAnswersCount < MAX_CORRECT_ANSWER_COUNT }
        if (notLearnedist.isEmpty()) return null

        val questionWord = notLearnedist.take(4).shuffled()
        val correctAnswer = questionWord.random()
        question = Question(
            variants = questionWord,
            correctAnswer = correctAnswer,
        )
        return question
    }

    fun checkAnswer(userAnswerIndex: Int?): Boolean {
        return question?.let {

            val correctAnswerId = it.variants.indexOf(it.correctAnswer)
            if (correctAnswerId == userAnswerIndex){
                it.correctAnswer.correctAnswersCount++
                saveDictionary(dictionary)
                true
            } else {
                false
            }

        } ?: false
    }

    private fun loadDictionary(): List<Word> {
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

    private fun saveDictionary(answers: List<Word>) {
        val wordsFile: File = File("words.txt")
        wordsFile.writeText(answers.toString())

        val fileContent = answers.joinToString("\n") {
            "${it.englishText}|${it.russianText}|${it.correctAnswersCount}"
        }
        wordsFile.writeText(fileContent)
    }
}

