package com.example.myquizz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class QuizAdapter(
    private val context: Context,
    private val quizList: MutableList<String>,
    private val listener:OnClickHandler
): RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view:View =LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val quiz = quizList[position]
    }

    override fun getItemCount(): Int {
        return quizList.size
    }

    class QuizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var question:TextView = itemView.findViewById(R.id.tvQuestion)
    }
}