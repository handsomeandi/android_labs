package com.example.androidlabs

import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    var currentIndex = 0

    private val questionBank = mutableListOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    val isCheater: Boolean
        get() = questionBank[currentIndex].isCheated

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionIsAnswered: Boolean
        get() = questionBank[currentIndex].isAnswered

    val currentQuestionIsAnsweredCorrectly: Boolean?
        get() = questionBank[currentIndex].isAnsweredCorrect

    val isAllQuestionsAnswered: Boolean
        get() = questionBank.all {
            it.isAnswered
        }

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = if (currentIndex > 0) currentIndex - 1 else questionBank.size - 1
    }

    fun setQuestionAnswered(isAnsweredCorrectly: Boolean) {
        questionBank[currentIndex] = questionBank[currentIndex].copy(
            isAnswered = true,
            isAnsweredCorrect = isAnsweredCorrectly
        )
    }

    fun getTestResult() = "Результат теста: ${
        (questionBank.count {
            it.isAnsweredCorrect == true
        }.toFloat() / questionBank.size.toFloat()) * 100
    }%"

    fun updateCheater(isCheating: Boolean) {
        questionBank[currentIndex] = questionBank[currentIndex].copy(
            isCheated = isCheating
        )
    }
}