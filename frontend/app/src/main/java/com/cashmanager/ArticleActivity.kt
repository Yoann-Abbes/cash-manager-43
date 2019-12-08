package com.cashmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_article.*


class ArticleActivity : AppCompatActivity() {

    internal var lan = listOf("Tel","Sumsung","Iphone")
    internal var prix = arrayOf("prix", 200, 300)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        button.setOnClickListener {
           // val message: String = textInputEditText.text.toString()
          //  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            val intent = Intent(this, PanierActivity ::class.java)
            startActivity(intent)

        }

        var lv = findViewById(R.id.lview) as ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lan)
        lv.adapter =adapter


        var lv2 = findViewById(R.id.lview2) as ListView
        val prodadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, prix)
        lv2.adapter =prodadapter





       // var pr = findViewById(R.id.lview2) as ListView
   //     val adapterView = arrayListOf(this, android.R.layout.simple_list_item_2,prix)
     //   pr.adapter = adapterView








     //   fun AddButtonRouge(button:View){
       //     Log.i("effect","effect")
         //   textInputEditText2.text = textInputEditText.text
      //  }


    }


}
