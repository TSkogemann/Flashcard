package com.example.thomasskogemann.flashcard

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView

import com.example.thomasskogemann.flashcard.adapters.ItemclickListener
import com.example.thomasskogemann.flashcard.adapters.QuestionRecycleAdapter
import com.example.thomasskogemann.flashcard.adapters.RecycleAdapter
import com.example.thomasskogemann.flashcard.data.model.Answer
import com.example.thomasskogemann.flashcard.data.model.Flashcard
import com.example.thomasskogemann.flashcard.data.model.Rating
import com.example.thomasskogemann.flashcard.data.model.User

import java.util.ArrayList
import java.util.Date

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_showcard.*

/**
 * Created by Thomas Skogemann on 27-09-2016.
 */
class ShowCardActivity : AppCompatActivity() {

    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mQuestionAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var mQuestionManager: RecyclerView.LayoutManager? = null
    private val flashcardData = Flashcard("tempCategory", ArrayList<Answer>(), "tempID", Rating(), "tempQuestion")
    private var currentUser: User? = null
    private var currentCard = 0
    private var answerNumber = 0
    private var redoMode = 0 // 0 = off 1 = on


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showcard)
        ButterKnife.bind(this)

        // use a linear layout manager
        mLayoutManager = LinearLayoutManager(this)
        mQuestionManager = LinearLayoutManager(this)
        show_content_recycler_view.layoutManager = mLayoutManager
        show_question_recycler_view!!.layoutManager = mQuestionManager

        //Setting user
        if (intent.hasExtra("dummyUser")) {
            currentUser = intent.getSerializableExtra("dummyUser") as User
            show_user_info!!.text = "" + currentUser!!.name
        }
        // setting view
        settingProgressInfo()
        setDateInfo()
        //show_category_info.setText(currentUser.getFlashcards().get(currentCard).getCategory());


        //Setting adapters
        mAdapter = RecycleAdapter(currentUser)
        mQuestionAdapter = QuestionRecycleAdapter(flashcardData, object : ItemclickListener {
            override fun onItemClicked(itemNumber: Int, data: String) {
                Log.i(ShowCardActivity::class.java.simpleName, data)
                answer.text = data
                answerNumber = itemNumber
            }
        })
        updateView()

        // setting adapters to views
        show_content_recycler_view.adapter = mAdapter
        show_question_recycler_view.adapter = mQuestionAdapter

        // setting onClickListeneres
        start_flash_card.setOnClickListener {
            show_content_recycler_view.visibility = View.GONE
            show_question_recycler_view.visibility = View.VISIBLE
            answer!!.visibility = View.VISIBLE
            show_progress_info!!.visibility = View.VISIBLE
            show_category_info!!.visibility = View.VISIBLE
            show_date_info!!.visibility = View.INVISIBLE
            start_flash_card!!.visibility = View.GONE
        }

        answer.setOnClickListener {
            // Guard should be changed, lowering currentCard by 1 is not a clean way to do it
            //// TODO: 14-10-2016
            if (currentUser!!.flashcards.size <= currentCard) {
                Log.d(currentCard.toString() + " - " + currentUser!!.flashcards.size, "")
                currentCard = currentCard - 1

            } else {

                val card = currentUser!!.flashcards[currentCard]
                if (card.answers[answerNumber].correct) {
                    // Correct answer
                    //TODO set rating
                    card.rating = calculateRating(true, "tempCorrectId", 5, Date(), 10)
                    nextCard()
                } else {
                    // Incorrect answer
                    card.rating = calculateRating(false, "tempIncorrectId", 1, Date(), 15)
                    nextCard()
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        currentCard = 0
    }

    private fun setDateInfo() {
        show_progress_info!!.visibility = View.INVISIBLE
        show_date_info.visibility = View.VISIBLE
        show_date_info.text = "" + Date()
    }

    private fun calculateRating(isCorrect: Boolean, id: String, rating: Int, date: Date, time: Int): Rating {
        val newRating = Rating()
        // setting is correct
        newRating.correct = isCorrect
        // setting id - should be autogenerated?
        newRating.id = id
        // setting rating - TODO needs a method to calculate how
        newRating.rating = rating
        // setting date
        newRating.solveDate = date
        // setting time it took to solve the question - TODO need a method
        newRating.time = time
        return newRating
    }

    fun nextCard() {
        currentCard++
        if (currentCard >= currentUser!!.flashcards.size) {
            deckDone()
        }
        updateView()
    }

    private fun updateView() {
        answer!!.text = "Answer"
        updateNextCard()
        settingProgressInfo()
        mQuestionAdapter!!.notifyDataSetChanged()
    }

    private fun updateNextCard() {
        if (redoMode == 0) {
            if (currentUser!!.flashcards.size > currentCard) {
                flashcardData.update(currentUser!!.flashcards[currentCard])
            }
        }
        if (redoMode == 1) {
            if (currentUser!!.flashcards.size > currentCard) {
                if (currentUser!!.flashcards[currentCard].rating.correct === false) {
                    flashcardData.update(currentUser!!.flashcards[currentCard])
                } else
                    nextCard()
            } else
                redoMode = 0
        }
    }

    private fun deckDone() {
        //todo
        redoIncorrectCards()
        if (redoMode == 0) {
            showResults()
        }

    }

    private fun redoIncorrectCards() {
        if (redoMode == 1) {
            redoMode = 0
            return
        }

        if (redoMode == 0) {
            redoMode = 1
            currentCard = 0
        }
    }

    private fun showResults() {
        //TODO
        // intent
        val intent = Intent(this@ShowCardActivity, ResultActivity::class.java)
        intent.putExtra("currentUser", currentUser)
        //change activity
        startActivity(intent)
        /*
        currentUser.nextDeck();
        currentCard = 0;
        currentDeck.update(currentUser.getDeckList().get(currentUser.getCurrentDeck()));
        show_deck_info.setText(currentUser.getDeckList().get(currentUser.getCurrentDeck()).getTitle());
        mQuestionAdapter.notifyDataSetChanged();
        */
    }


    // methods used for creating Span text. Colored text
    private fun settingProgressInfo() {
        if (redoMode == 0) {
            val coloredText = setIncorrectAnswers()
            val fullText = setCardsLeft() + "/" + coloredText
            val color = Color.RED
            setColor(show_progress_info, fullText, coloredText, color)
            setCategoryInfo()

        }
        if (redoMode == 1) {
            val incorrectLeft = Integer.parseInt(setIncorrectAnswers())
            show_progress_info!!.text = "TODO"
            setCategoryInfo()

        }
    }

    private fun setCategoryInfo() {
        // setting card category
        if (currentUser!!.flashcards.size > currentCard) {
            show_category_info!!.text = currentUser!!.flashcards[currentCard].category
        }
    }

    private fun setColor(view: TextView, fulltext: String, subtext: String, color: Int) {
        view.setText(fulltext, TextView.BufferType.SPANNABLE)
        val str = view.text as Spannable
        val i = fulltext.indexOf("/") + 1
        str.setSpan(ForegroundColorSpan(color), i, i + subtext.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    }

    private fun setCardsLeft(): String {
        var cards = 0
        if (redoMode == 1) {
            for (flashcard in currentUser!!.flashcards) {
                if (flashcard.rating.correct != null) {
                    if (flashcard.rating.correct === false) {
                        cards++
                    }
                }
            }
        }

        if (redoMode == 0) {
            cards = currentUser!!.flashcards.size - currentCard
        }
        return "" + cards
    }

    private fun setIncorrectAnswers(): String {

        var res = 0

        for (flashcard in currentUser!!.flashcards) {
            if (flashcard.rating.correct != null) {
                if (flashcard.rating.correct === false) {
                    res++
                }
            }
        }
        return "" + res
    }

}
