package com.cashmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_paiementcheque.*

class PaiementchequeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paiementcheque)

    button3.setOnClickListener {
        val intent = Intent(this, Validation ::class.java)
        startActivity(intent)
    }
    }

     }

