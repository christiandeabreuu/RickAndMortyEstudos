package com.example.myappbancodados.rickandmorty.ui.character.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myappbancodados.R
import com.example.myappbancodados.databinding.FragmentCharacterListBinding
import com.example.myappbancodados.rickandmorty.data.model.CharacterResult
import com.example.myappbancodados.rickandmorty.ui.adapter.CharacterAdapter
import com.example.myappbancodados.rickandmorty.ui.character.viewmodel.CharacterViewModel
import com.example.myappbancodados.viewstate.ViewState
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment(R.layout.fragment_character_list) {
    private lateinit var binding: FragmentCharacterListBinding

    //    private val viewModel: CharacterViewModel by lazy {
//        ViewModelProvider(this)[CharacterViewModel::class.java]
//    }
    private val viewModel: CharacterViewModel by viewModel()

    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter(this::goToCharacterDetail)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllCharactersNetwork()
        initObserver()
        initRecyclerview()
    }

    private fun initRecyclerview() {
        binding.rvCharacterList.adapter = characterAdapter
        binding.rvCharacterList.layoutManager = GridLayoutManager(context, 2)
    }

    private fun getAllCharactersNetwork() {
        viewModel.getAllCharactersNetwork()
    }

    private fun initObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.characterListState.collect {
                when (it) {
                    is ViewState.Loading -> {
                        binding.pbLoading.isVisible = true
                    }
                    is ViewState.Error -> {
                        Toast.makeText(
                            context,
                            "Nao foi possivel carregar as imagens",
                            Toast.LENGTH_LONG
                        ).show()
                        binding.pbLoading.isVisible = false
                    }
                    is ViewState.Success -> {
                        characterAdapter.updateList(it.data.toMutableList())
                        binding.pbLoading.isVisible = false
                    }
                }
            }
        }
//        viewModel.characterListState.observe(this.viewLifecycleOwner) {
//            when (it) {
//                is ViewState.Success -> {
////                    TODO()
////                adapter.submitList(it.data)
////                oldAdapter.updateList(it.data.toMutableList())
//                    characterAdapter.updateList(it.data.toMutableList())
//                }
//                is ViewState.Error -> {
//                    Toast.makeText(
//                        context,
//                        "Nao foi possivel carregar as imagens",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//                else -> {}
//            }
//        }
//        viewModel.loading.observe(this.viewLifecycleOwner) {
//            when (it) {
//                is ViewState.Loading -> {
//                    binding.pbLoading.isVisible = it.loading == true
//                }
//                else -> {}
//            }
//        }
    }

    private fun goToCharacterDetail(character: CharacterResult) {
        val bundle = bundleOf("KEY" to character)

        NavHostFragment.findNavController(this).navigate(
            R.id.action_characterListFragment_to_characterDetailFragment, bundle
        )
    }
}