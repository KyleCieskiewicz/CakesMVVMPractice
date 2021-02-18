package com.example.cakesmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cakesmvvm.R
import com.example.cakesmvvm.adapter.CakeClick
import com.example.cakesmvvm.adapter.CakesAdapter
import com.example.cakesmvvm.databinding.ActivityMainBinding
import com.example.cakesmvvm.model.Cake_model
import com.example.cakesmvvm.viewmodel.CakesViewmodel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), CakesListener {
    private var cakesAdapter: CakesAdapter? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(CakesViewmodel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.setLifecycleOwner(this)
        binding.viewmodel = viewModel
        viewModel.cakesListener = this

        cakesAdapter = CakesAdapter(CakeClick {
            viewModel.displayCakeToast(it)
        })

        binding.root.recyclerview_list.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = cakesAdapter
        }

        viewModel.cakesList?.observe(this,
                {
                    cakesList ->
                    cakesList?.apply {
                        cakesAdapter?.cakesList = this
                    }
                })

//        setContentView(R.layout.activity_main)

        //point of databinding:
        //set values to the xml via the activity/fragment (viewcontroller)
        //two way databinding is when this happens both ways, meaning that the items updated by the user
        //in the layout will also be updated in the activity/fragment itself

        //no longer needed with two way databinding
//        textView.getText(//whatever the user inputted))

        //a new binding class will be created called [ActivityYourLayout]Binding

        //viewbinding (part of the jetpack library)
        //basically all of the functions of kotlin synthetics
        //faster compile time than databinding, but does not provide two-way databinding functionalities

    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onItemClick(cakeModel: Cake_model) {
        Toast.makeText(this, cakeModel.title, Toast.LENGTH_LONG).show()
    }
}