package com.cashmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_paiement.*

class PaiementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paiement)

        button6.setOnClickListener {
            val intent = Intent(this, PaiementNfc ::class.java)
            startActivity(intent)
    }
        button7.setOnClickListener {
            val intent = Intent(this, PaiementchequeActivity ::class.java)
            startActivity(intent)
        }
}
}