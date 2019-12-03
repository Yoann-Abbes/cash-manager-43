package com.cashmanager


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_panier.*

class PanierActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panier)

        button5.setOnClickListener {

            val intent = Intent(this, PaiementActivity ::class.java)
            startActivity(intent)

    }

    }

}
