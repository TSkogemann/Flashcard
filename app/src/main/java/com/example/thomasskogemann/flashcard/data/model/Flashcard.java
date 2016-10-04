package com.example.thomasskogemann.flashcard.data.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Thomas Skogemann on 27-09-2016.
 */
public class Flashcard implements Serializable {
    //Bør kort ikke have en titel, når de skal vises i oversigt?
    ArrayList<String> answers = new ArrayList<>();
    int id;
    String question;
    //Der skal være en correct Answer position
    int correctAnswerPosition;

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void update(Flashcard other) {
        answers.clear();
        answers.addAll(other.answers);
        question = other.question;
        id = other.id;
        correctAnswerPosition = other.correctAnswerPosition;
    }


    public Flashcard(ArrayList<String> answers, int correctAnswerPosition, int id, String question) {
        this.answers = answers;
        this.id = id;
        this.question = question;
        this.correctAnswerPosition = correctAnswerPosition;
    }

    public static Flashcard parseFrom(JSONObject json) throws JSONException {
        //TODO Id og correct position findes ikke i database, derfor sat til 0

        String question = json.optString("question");
        ArrayList<String> answers = new ArrayList<>();
        JSONArray answersJson = json.getJSONArray("answers");
        for (int i=0; i<answersJson.length(); i++){
            answers.add(answersJson.getJSONObject(i).getString("text"));
        }
        Flashcard res = new Flashcard(answers,0,0,question);
        return res;
    }
}
