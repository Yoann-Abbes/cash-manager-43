package com.cashmanager

import android.content.Intent
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var lv: ListView? = null
    private var customAdapter: CustomAdapter? = null
    private var btnnext: Button? = null
    private val fruitlist = arrayOf("Citron : 1euro ", "Oranges: 1euro ", "Cerises: 1euro ", "Pomme: 1euro ", "kiwi: 1euro ","Banane: 1euro ","Framboise: 1euro ","Fraise: 1euro ")

    private val model: ArrayList<Model>
        get() {
            val list = ArrayList<Model>()
            for (i in 0..7) {

                val model = Model()
                model.setNumbers(1)
                model.setFruits(fruitlist[i])
                list.add(model)
            }
            return list
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lv = findViewById(R.id.lv) as ListView
        btnnext = findViewById(R.id.next) as Button

        modelArrayList = model
        customAdapter = CustomAdapter(this)
        lv!!.adapter = customAdapter

        btnnext!!.setOnClickListener {
            val intent = Intent(this@MainActivity, NextActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        lateinit var modelArrayList: ArrayList<Model>
    }
}
