package com.example.thomasskogemann.flashcard.data.model

import java.io.Serializable
import java.util.Date

class Rating() : Serializable {
    var id: String? = null
    var rating: Int = 0
    var time: Int = 0
    var correct: Boolean? = null
    var solveDate: Date? = null
}
