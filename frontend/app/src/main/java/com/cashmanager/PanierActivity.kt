package com.cashmanager


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_panier.*

class PanierActivity : AppCompatActivity() {
    internal var lang = arrayOf("Android 9", "Iphone 10 ")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panier)

        button5.setOnClickListener {

            val intent = Intent(this, PaiementActivity ::class.java)
            startActivity(intent)

          }
        var lv = findViewById(R.id.lview3) as ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lang)
        lv.adapter =adapter
    }

}
