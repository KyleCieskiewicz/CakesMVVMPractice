package com.example.cakesmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cakesmvvm.model.Cake_model
import com.example.cakesmvvm.repositories.CakeRepository
import com.example.cakesmvvm.util.Coroutines
import com.example.cakesmvvm.view.CakesListener

//dependency injection will allow me to instantiate CAkesRepository under the hood so that CAkesViewModel
//does not have to initialize it
//@Inject
class CakesViewmodel: ViewModel(//may have multiple args
) {
    var cakesList = MutableLiveData<List<Cake_model>>()
    var cakesListener: CakesListener? = null

    init {
        refreshList()
    }

    fun refreshList() {
        //lets say that we're updating our cakesLivedata
        //first we retrieve the readable onlyversion and set it equal to our mutable Live data


        //doInbackground process
        //updateUI thread {

        Coroutines.main {
//            val cakesResponse = CakeRepository().getCakes()
            val cakesResponse = CakeRepository().getCakes()
            if (cakesResponse.isSuccessful) {
                cakesResponse.body()?.let {
                    //this is where we add or change the mutable livedata value
                    cakesList?.value = it
                    Log.d("cakes_list", cakesList.toString())
                }
            } else {
                cakesListener?.onFailure(cakesResponse.message())
            }
        }
    }

    fun displayCakeToast(cakeModel: Cake_model) {
        cakesListener?.onItemClick(cakeModel)
    }
}