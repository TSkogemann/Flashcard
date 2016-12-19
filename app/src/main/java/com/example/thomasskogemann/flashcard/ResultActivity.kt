package com.example.thomasskogemann.flashcard

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.ListView

import com.example.thomasskogemann.flashcard.adapters.ItemclickListener
import com.example.thomasskogemann.flashcard.adapters.QuestionRecycleAdapter
import com.example.thomasskogemann.flashcard.adapters.RecycleAdapter
import com.example.thomasskogemann.flashcard.adapters.ResultAdapter
import com.example.thomasskogemann.flashcard.data.model.User
import com.example.thomasskogemann.flashcard.data.model.Utils

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_result.*

/**
 * Created by Thomas Skogemann on 13-10-2016.
 */
class ResultActivity : AppCompatActivity() {
    private var user: User?=null
    private var mResultAddapter: ResultAdapter?=null
    private var mLayoutManager: RecyclerView.LayoutManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        ButterKnife.bind(this)

        // setting user
        if (intent.hasExtra("currentUser")) {
            user = intent.getSerializableExtra("currentUser") as User

        }

        // setting layout manager
        mLayoutManager = LinearLayoutManager(this)
        result_list.layoutManager = mLayoutManager


        // Adapter
        mResultAddapter = ResultAdapter(user)
        // setting adapters to views
        result_list.adapter = mResultAddapter

        // setting onclick Listeners
        get_more_cards_btn.setOnClickListener {
            // generating dummy info
            val bla = MainActivity()
            val dummyUser = User.generateDummyUser(Utils.randomString + "ID", "dummyUser", 10, 4)

            // intent
            val intent = Intent(this@ResultActivity, ShowCardActivity::class.java)
            intent.putExtra("dummyUser", dummyUser)
            //change activity
            startActivity(intent)
        }
    }
}
