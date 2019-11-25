package com.cashmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_article.*


class ArticleActivity : AppCompatActivity() {

    var array = arrayOf("Iphone 8", "Sumsung 8", "Sumsung 8Plus", "Iphone 11")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        button.setOnClickListener {
            val message: String = textInputEditText.text.toString()
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            val intent = Intent(this, PanierActivity ::class.java)
            startActivity(intent)
        }

        fun AddButtonRouge(button:View){
            Log.i("effect","effect")
            textInputEditText2.text = textInputEditText.text
        }


    }


}
