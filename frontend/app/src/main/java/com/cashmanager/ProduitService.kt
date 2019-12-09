package com.cashmanager

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ProduitService {
    @GET("/produits")
    suspend fun listProduits(): Response<List<Produit>>
}