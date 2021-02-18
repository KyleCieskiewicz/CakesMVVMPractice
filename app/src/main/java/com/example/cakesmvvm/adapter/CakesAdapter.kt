package com.example.cakesmvvm.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView;

import com.example.cakesmvvm.R
import com.example.cakesmvvm.databinding.CardRowBinding
import com.example.cakesmvvm.model.Cake_model;


class CakesAdapter(val listener: CakeClick): RecyclerView.Adapter<CakesViewHolder>()  {

        var cakesList: List<Cake_model> = emptyList()
        //this "set" is a convenience method for vars, similar to a setter in java test
        set(value) {
                field = value
                //updates the list every time it is set, and notifies observers when changed;
                //also invalidates every item in the recyclerview list
                notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakesViewHolder {
                val withDataBinding: CardRowBinding = DataBindingUtil.inflate(
                        //takes a layoutInflater arg, which can be initiated using LayoutInflater class
                        LayoutInflater.from(parent.context),
                        //Int arg, meaning the id of the layout to be inflated
                        CakesViewHolder.LAYOUT,
                        //viewgroup arg
                        parent,
                        false
                )
                return CakesViewHolder(withDataBinding)
        }

        override fun onBindViewHolder(holder: CakesViewHolder, position: Int) {
                holder.viewDatabinding.also {
                        it.cake = cakesList[position]
                        it.clickCallback = listener
                }
        }

        override fun getItemCount(): Int {
                return cakesList.size
        }
}

class CakesViewHolder(val viewDatabinding: CardRowBinding): RecyclerView.ViewHolder(viewDatabinding.root) {
        companion object {
                @LayoutRes
                val LAYOUT = R.layout.card_row
        }
}

class CakeClick(val block: (Cake_model)->Unit) {
        fun onClick(cake: Cake_model) = block(cake)
}