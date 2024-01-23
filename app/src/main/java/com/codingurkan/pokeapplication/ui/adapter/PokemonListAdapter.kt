package com.codingurkan.pokeapplication.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingurkan.pokeapplication.data.remote.responses.Result
import com.codingurkan.pokeapplication.databinding.ItemPokemonListBinding

class PokemonListAdapter(private val list : List<Result>,
                        private var itemCLickListener: ItemCLickListener) : RecyclerView.Adapter<PokemonListAdapter.PokemonVH>() {

    private val TAG = javaClass.simpleName

    inner class PokemonVH(val binding: ItemPokemonListBinding) : RecyclerView.ViewHolder(binding.root)

    interface ItemCLickListener {
        fun click(data : Result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonVH {
        val view = ItemPokemonListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PokemonVH(view)
    }

    override fun onBindViewHolder(holder: PokemonVH, position: Int) {
        with(holder.binding){
            val data = list[position]
            characterName.text = data.name
            Log.d("agt", "onBindViewHolder: ")

            (characterImage ?: characterCardView).setOnClickListener {
                itemCLickListener.click(data)
            }
        }
    }

    override fun getItemCount() = list.size
}