package com.example.myappbancodados.rickandmorty.ui.character.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myappbancodados.R
import com.example.myappbancodados.databinding.FragmentCharacterDetailBinding
import com.example.myappbancodados.rickandmorty.data.model.CharacterResult
import com.example.myappbancodados.utils.Constants.NAME
import com.squareup.picasso.Picasso

class CharacterDetailFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCharacterDetail()
    }

    @SuppressLint("SetTextI18n") // TIROU AS MARCACOES DOS TEXTOS EX-  "ID : " + IT.ID
    private fun getCharacterDetail() {
        val character = arguments?.getParcelable<CharacterResult>("KEY")

        character?.let {
            Picasso.get().load(it.image).into(binding.ivDetailCharacter)
            binding.tvDetailName.text = NAME + it.name
            binding.tvDetailStatus.text = "STATUS : " + it.status
            binding.tvDetailSpecies.text = "ESPECIE : " + it.species
            binding.tvDetailGender.text = getString(R.string.gender) + it.gender
            binding.tvDetailId.text = "Id : " + it.id.toString()

        }
    }
}