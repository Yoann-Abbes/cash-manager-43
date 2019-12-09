package com.cashmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_paiement_nfc.*

class PaiementNfc : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paiement_nfc)

        validate.setOnClickListener {
            val intent = Intent(this, Validation ::class.java)
            startActivity(intent)
        }
    }

}
