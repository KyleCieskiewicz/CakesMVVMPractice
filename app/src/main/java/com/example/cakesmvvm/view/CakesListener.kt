package com.example.cakesmvvm.view

import com.example.cakesmvvm.model.Cake_model

interface CakesListener {
    fun onItemClick(cakeModel: Cake_model)
    fun onFailure(message: String)
}