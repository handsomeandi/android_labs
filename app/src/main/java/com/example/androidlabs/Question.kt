package com.example.androidlabs

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean, val isAnswered: Boolean = false, val isAnsweredCorrect: Boolean? = null)