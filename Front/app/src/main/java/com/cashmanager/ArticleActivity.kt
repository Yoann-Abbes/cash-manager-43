package com.cashmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_article.*


class ArticleActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
    }

    fun AddButtonRouge(button:View){
        Log.i("effect","effect")
        textInputEditText2.text = textInputEditText.text
    }
}
