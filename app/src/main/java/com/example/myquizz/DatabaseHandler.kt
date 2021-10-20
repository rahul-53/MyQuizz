package com.example.myquizz

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHandler(private val context: Context):SQLiteOpenHelper(context,"quizdb",null,1) {
    companion object{
        const val TABLE_NAME = "quiz_database"
        const val ID = "id"
        const val QUESTION = "question"
        const val OPTIONA = "optionA"
        const val OPTIONB = "optionB"
        const val OPTIONC = "optionC"
        const val OPTIOND = "optionD"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery= "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY," +
                "$QUESTION TEXT," +
                "$OPTIONA TEXT," +
                "$OPTIONB TEXT," +
                "$OPTIONC TEXT," +
                "$OPTIOND TEXT)"
        db?.execSQL(createQuery)
    }

    fun createQuestion(question:String, optionA:String,optionB:String,optionC:String,optionD:String){
        val db:SQLiteDatabase =writableDatabase
        val values =ContentValues()
        values.put(QUESTION, question)
        values.put(OPTIONA, optionA)
        values.put(OPTIONB, optionB)
        values.put(OPTIONC, optionC)
        values.put(OPTIOND, optionD)
        val id = db.insert(TABLE_NAME, null,values)
        if (id.toInt() == -1){
            //Error
            Toast.makeText(context, "Error while inserting", Toast.LENGTH_SHORT).show()
        }else{
            //Success
            Toast.makeText(context, "Data inserted successfully", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateQuestion(id:Int, newQuestion:String, newOptionA:String,newOptionB:String,newOptionC:String,newOptionD:String){
        val db = writableDatabase
        val values = ContentValues()
        values.put(QUESTION, newQuestion)
        values.put(OPTIONA, newOptionA)
        values.put(OPTIONB, newOptionB)
        values.put(OPTIONC, newOptionC)
        values.put(OPTIOND, newOptionD)

        val affectedRows =db.update(TABLE_NAME, values,"id=$id", null)

        if (affectedRows > 0){
            //Success
            Toast.makeText(context, "Data Updated successfully", Toast.LENGTH_SHORT).show()

        }else{
            //Failed
            Toast.makeText(context, "Error, data not updated", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteQuestion(id: Int){
         val db= writableDatabase
        val affectedRows =db.delete(TABLE_NAME,"id = $id",null)
        if (affectedRows > 0){
            //Success
            Toast.makeText(context, "Data Deleted successfully", Toast.LENGTH_SHORT).show()

        }else{
            //Failed
            Toast.makeText(context, "Error while deleting", Toast.LENGTH_SHORT).show()

        }
    }

    fun getQuestions():MutableList<QuizModal>{
        val listQuiz:MutableList<QuizModal> = mutableListOf<QuizModal>()
        val db:SQLiteDatabase = readableDatabase

        val query = "select * from $TABLE_NAME"
        val cursor:Cursor = db.rawQuery(query, null)

        if (cursor!= null && cursor.count>0){
            cursor.moveToFirst()
            do {
                var idIndex:Int =cursor.getColumnIndex(ID)
                var questionIndex:Int =cursor.getColumnIndex(QUESTION)
                var optionAIndex:Int =cursor.getColumnIndex(OPTIONA)
                var optionBIndex:Int =cursor.getColumnIndex(OPTIONB)
                var optionCIndex:Int =cursor.getColumnIndex(OPTIONC)
                var optionDIndex:Int =cursor.getColumnIndex(OPTIOND)

                val id: Int = cursor.getInt(idIndex)
                val question:String= cursor.getString(questionIndex)
                val optionA:String = cursor.getString(optionAIndex)
                val optionB:String = cursor.getString(optionBIndex)
                val optionC:String = cursor.getString(optionCIndex)
                val optionD:String = cursor.getString(optionDIndex)

                val quizModal = QuizModal(id, question, optionA, optionB, optionC, optionD)
                listQuiz.add(quizModal)

            }while (cursor.moveToNext())

        }
        return listQuiz
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}