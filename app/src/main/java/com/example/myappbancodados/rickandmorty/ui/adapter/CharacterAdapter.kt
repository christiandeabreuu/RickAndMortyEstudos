package com.example.myappbancodados.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myappbancodados.databinding.ItemCharacterBinding
import com.example.myappbancodados.rickandmorty.data.model.CharacterResult
import com.squareup.picasso.Picasso

class CharacterAdapter(private val clickDetail : (CharacterResult) -> Unit,) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var listCharacter = mutableListOf<CharacterResult>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listCharacter[position]
        holder.showInfo(item)
        holder.binding.cvItemCharacter.setOnClickListener {
        clickDetail(item)
        }
    }

    override fun getItemCount(): Int = listCharacter.size

    inner class ViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun showInfo(characterResult: CharacterResult) {
            Picasso.get().load(characterResult.image).into(binding.ivItemCharacter)
//            binding.tvItemNameCharacter.text = characterResult.name
            binding.tvItemId.text = characterResult.id.toString()
        }
    }

    fun updateList(newList: MutableList<CharacterResult>) {
        listCharacter = newList
        notifyDataSetChanged()
    }

}