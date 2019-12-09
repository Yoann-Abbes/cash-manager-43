package com.cashmanager


//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.graphics.SumPathEffect
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_next.*
import kotlinx.android.synthetic.main.activity_next.view.*
import kotlinx.android.synthetic.main.lv_item.view.*

class NextActivity : AppCompatActivity() {

    private var tv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        tv = findViewById(R.id.tv) as TextView

        for (i in 0..7) {
            val text = tv!!.text.toString()
            tv!!.text = text + MainActivity.modelArrayList.get(i).getFruits() + " --->  " + MainActivity.modelArrayList.get(i).getNumbers() + "\n"

            textView4.text
        }


        button9.setOnClickListener {
            val intent = Intent(this, PaiementActivity ::class.java)
            startActivity(intent)
        }



    }

}
