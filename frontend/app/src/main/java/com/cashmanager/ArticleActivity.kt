package com.cashmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_article.*
import android.widget.Toast


class ArticleActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)



        btnSendMsgToNextActivity.setOnClickListener {
            val message: String = textInputEditText.text.toString()
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            val intent = Intent(this, PanierActivity ::class.java)
            startActivity(intent)
        }


    }

    fun AddButtonRouge(button:View){
        Log.i("effect","effect")
        textInputEditText2.text = textInputEditText.text
    }


}


