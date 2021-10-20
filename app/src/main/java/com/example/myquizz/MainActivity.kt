package com.example.myquizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickHandler {
    private var questionList:MutableList<QuizModal> = mutableListOf()
    lateinit var mAdapter: QuizAdapter
    lateinit var dbHandler: DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dbHandler =DatabaseHandler(this)
        questionList = dbHandler.getQuestions()

        mAdapter = QuizAdapter(this, questionList,this)

        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter  = mAdapter

        addBtn.setOnClickListener {
            dbHandler.createQuestion("What is the capital of India?","New Delhi",
                "Bangalore","Chennai","Mumbai")
            dbHandler.createQuestion("What is the capital of Nepal?","New Delhi",
                "Bangalore","Chennai","Kathmandu")
            questionList.clear()
            questionList.addAll(dbHandler.getQuestions())
            mAdapter.notifyDataSetChanged()
        }


    }

    override fun onEditClick(quiz:QuizModal) {
        dbHandler.updateQuestion(quiz.id,"What is the capital of Uganda?","New Delhi",
            "Bangalore","Chennai","Mumbai")
        questionList.clear()
        questionList.addAll(dbHandler.getQuestions())
        mAdapter.notifyDataSetChanged()
    }

    override fun onDeleteClick(quiz:QuizModal) {
        dbHandler.deleteQuestion(quiz.id)
        questionList.clear()
        questionList.addAll(dbHandler.getQuestions())

        mAdapter.notifyDataSetChanged()
    }
}