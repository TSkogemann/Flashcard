package com.example.thomasskogemann.flashcard.data.model

import org.json.JSONException
import org.json.JSONObject

import java.io.Serializable
import java.util.ArrayList

//Bør kort ikke have en titel, når de skal vises i oversigt?
class Flashcard(var category: String, var answers: ArrayList<Answer>, var id: String, var rating: Rating, var question: String) : Serializable {

    fun update(other: Flashcard) {
        answers.clear()
        category = other.category
        answers.addAll(other.answers)
        id = other.id
        rating = other.rating
        question = other.question
    }

    companion object {

        @Throws(JSONException::class)
        fun parseFrom(json: JSONObject): Flashcard {
            //TODO Id og correct position findes ikke i database, derfor sat til 0
            /*
        String question = json.optString("question");
        ArrayList<String> answers = new ArrayList<>();
        JSONArray answersJson = json.getJSONArray("answers");
        for (int i=0; i<answersJson.length(); i++){
            answers.add(answersJson.getJSONObject(i).getString("text"));
        }
        Flashcard res = new Flashcard(answers,0,0,question);
        return res;*/

            // TODO, temp info
            val category = "tempCategory"
            val answers = ArrayList<Answer>()
            val id = "tempID"
            val rating = Rating()
            val question = "tempQuestion"
            val flashcard = Flashcard(category, answers, id, rating, question)
            return flashcard
        }

        fun createDummyFlashCard(numberOfQuestions: Int): Flashcard {
            val category = Utils.randomString + "Category"
            val id = Utils.randomString + "ID"
            val question = "Her står spørgsmålet! Lalalal bla bla " + Utils.randomString
            val answers = ArrayList<Answer>()
            val rating = Rating()

            for (i in 0..numberOfQuestions - 1) {
                val answer = Answer(Utils.randomString, Utils.randomString, Utils.getRandomBoolean(i))
                answers.add(answer)


            }
            val flashcard = Flashcard(category, answers, id, rating, question)
            return flashcard
        }
    }
}