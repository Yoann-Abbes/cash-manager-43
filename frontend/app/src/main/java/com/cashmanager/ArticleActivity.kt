package com.cashmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_article.*
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*


class Produit(val name: String, val prix: Int)

class ArticleActivity : AppCompatActivity() {

    val produitName: MutableList<String> = ArrayList()
    val prix: MutableList<Int> = ArrayList()

    var rxAdapter = RxJava2CallAdapterFactory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val url = "http://18.191.247.228:8080/"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(rxAdapter)
            .build()

        val service = retrofit.create(ProduitService::class.java)
CoroutineScope(Dispatchers.IO).launch {
    val response = service.listProduits()
    withContext(Dispatchers.Main){
        try {
            if (response.isSuccessful) {
                 println("HERE is ALL COURSES FROM HEROKU SERVER:")
            } else {
                println("Error: ${response.code()}")
            }
        } catch (e: HttpException) {
            println("Exception ${e.message}")
        } catch (e: Throwable) {
            println("Ooops: Something else went wrong")
        }
    }
}
        /*

val produitRequest = service.listProduits()
produitRequest.enqueue(object : Callback<List<Produit>> {
override fun onResponse(call: Call<List<Produit>>, response: Response<List<Produit>>) {
val allProduits = response.body()
if (allProduits != null) {
for(c in allProduits) {
    produitName.add(c.name)
    prix.add(c.prix)
    // println("HERE is ALL COURSES FROM HEROKU SERVER:")
}
// println(produitName)

}
println(produitName)


}

override fun onFailure(call: Call<List<Produit>>, t: Throwable) {
error("KO")
}
})
*/





        button.setOnClickListener {
            // val message: String = textInputEditText.text.toString()
            //  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            var lv = findViewById(R.id.lview) as ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, produitName)
            lv.adapter = adapter

            val intent = Intent(this, PanierActivity::class.java)
            startActivity(intent)

        }




        var lv2 = findViewById(R.id.lview2) as ListView
        val prodadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, prix)
        lv2.adapter = prodadapter

        getUsers()

        println("VZEIGHZEUZEIZEIYGYZ")

    }

    fun getUsers() {
        val tanUrl = "http://18.191.247.228:8080/produits"

    }
}

