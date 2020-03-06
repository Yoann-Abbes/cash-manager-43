package com.cashmanager


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PanierActivity : AppCompatActivity() {

    private var tv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        tv = findViewById(R.id.tv) as TextView

        for (i in 0..4) {
            val text = tv!!.text.toString()
            tv!!.text = text + MainActivity.modelArrayList.get(i).getFruits() + " -> " + MainActivity.modelArrayList.get(i).getNumbers() + "\n"
        }
    }

}
