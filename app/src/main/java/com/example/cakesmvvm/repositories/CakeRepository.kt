package com.example.cakesmvvm.repositories

import com.example.cakesmvvm.api.ApiInterface
import com.example.cakesmvvm.api.RetrofitClient
import com.example.cakesmvvm.model.Cake_model
import retrofit2.Response

class CakeRepository() {
    suspend fun getCakes(): Response<List<Cake_model>> {
        return RetrofitClient.getCakeService(ApiInterface::class.java).getCakeslist()
    }
}