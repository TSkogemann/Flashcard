package com.example.thomasskogemann.flashcard.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Thomas Skogemann on 27-09-2016.
 */
public class Flashcard implements Serializable {
    //Bør kort ikke have en titel, når de skal vises i oversigt?
    String category;
    ArrayList<Answer> answers = new ArrayList<>();
    String id;
    Rating rating = new Rating();
    String question;

    public Flashcard(String category, ArrayList<Answer> answers, String id, Rating rating, String question) {
        this.category = category;
        this.answers = answers;
        this.id = id;
        this.rating = rating;
        this.question = question;
    }

    public void update(Flashcard other) {
        answers.clear();
        category = other.category;
        answers.addAll(other.answers);
        id = other.id;
        rating = other.rating;
        question = other.question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public static Flashcard parseFrom(JSONObject json) throws JSONException {
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
        String category = "tempCategory";
        ArrayList<Answer> answers = new ArrayList<>();
        String id = "tempID";
        Rating rating = new Rating();
        String question = "tempQuestion";
        Flashcard flashcard = new Flashcard(category, answers, id, rating, question);
        return flashcard;
    }

    public static Flashcard createDummyFlashCard(int numberOfQuestions) {
        String category = Utils.getRandomString() + "Category";
        String id = Utils.getRandomString() + "ID";
        String question = "Her står spørgsmålet! Lalalal bla bla " + Utils.getRandomString();
        ArrayList<Answer> answers = new ArrayList<>();
        Rating rating = new Rating();

        for (int i = 0; i < numberOfQuestions; i++) {
            Answer answer = new Answer(Utils.getRandomString(), Utils.getRandomString(), Utils.getRandomBoolean(i));
            answers.add(answer);


        }
        Flashcard flashcard = new Flashcard(category, answers, id, rating, question);
        return flashcard;
    }
}