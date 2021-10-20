package com.example.myquizz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuizAdapter(
    private val context: Context,
    private val questionList: MutableList<QuizModal>,
    private val listener:OnClickHandler
): RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view:View =LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return QuizViewHolder(view)
    }


    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val questions = questionList[position]
        holder.question.text=questions.question
        holder.option_a.text = questions.optionA
        holder.option_b.text = questions.optionB
        holder.option_c.text = questions.optionC
        holder.option_d.text = questions.optionD

        holder.mIbEdit.setOnClickListener{
            listener.onEditClick(questions)
        }
        holder.mIbDelete.setOnClickListener {
            listener.onDeleteClick(questions)
        }


    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    class QuizViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var question:TextView = itemView.findViewById(R.id.tvQuestion)
        var option_a:TextView = itemView.findViewById(R.id.tvOptionA)
        var option_b:TextView = itemView.findViewById(R.id.tvOptionB)
        var option_c:TextView = itemView.findViewById(R.id.tvOptionC)
        var option_d:TextView = itemView.findViewById(R.id.tvOptionD)
        var mIbEdit: ImageButton = itemView.findViewById(R.id.ivEdit)
        var mIbDelete: ImageButton = itemView.findViewById(R.id.ivDelete)

    }
}