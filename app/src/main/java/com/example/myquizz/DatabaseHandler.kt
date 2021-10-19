package com.example.myquizz

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHandler(private val context: Context):SQLiteOpenHelper(context,"quizdb",null,1) {
    companion object{
        val TABLE_NAME = "quiz_database"
        val ID = "id"
        val QUESTION = "question"
        val OPTIONA = "optionA"
        val OPTIONB = "optionB"
        val OPTIONC = "optionC"
        val OPTIOND = "optionD "
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

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}