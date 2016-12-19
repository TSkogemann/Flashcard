package com.example.thomasskogemann.flashcard.data.model

import java.io.Serializable
import java.util.ArrayList
import java.util.Random

class User(val id: String, val name: String, val flashcards: ArrayList<Flashcard>) : Serializable {

    companion object {

        fun generateDummyUser(id: String, name: String, numberOfFlashcards: Int, numberOfQuestions: Int): User {
            val cards = ArrayList<Flashcard>()
            for (i in 0..numberOfFlashcards - 1) {
                cards.add(Flashcard.createDummyFlashCard(numberOfQuestions))
            }
            val dummyUser = User(id, name, cards)
            return dummyUser
        }
    }


}
