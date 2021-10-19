package com.example.myquizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickHandler {
    private val quizList:MutableList<String> = mutableListOf()
    lateinit var mAdapter: QuizAdapter
    lateinit var dbHandler: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quizList.add("hell")
        quizList.add("hell")
        quizList.add("hell")
        quizList.add("hell")
        quizList.add("hell")
        quizList.add("hell")

        mAdapter = QuizAdapter(this, quizList,this)

        dbHandler =DatabaseHandler(this)

        addBtn.setOnClickListener {
            dbHandler.createQuestion("What is the capital of India?","New Delhi",
                "Bangalore","Chennai","Mumbai")
        }

        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter  = mAdapter
    }

    override fun onEditClick(string: String) {
        TODO("Not yet implemented")
    }

    override fun onDeleteClick(string: String) {
        TODO("Not yet implemented")
    }
}