package com.example.myappbancodados.login.ui.register

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.myappbancodados.R
import com.example.myappbancodados.databinding.FragmentRegisterBinding
import com.example.myappbancodados.login.data.local.model.User
import com.google.android.material.internal.ViewUtils.hideKeyboard


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validateRegister()
        returnToLoginButton()
        initObservers()
    }

    private fun validateRegister() {
        binding.bvRegisterNow.setOnClickListener {
            hideKeyboard()
            val user = getData()
            if (user != null) {
                viewModel.validateDataUser(user)
            } else {
                showFieldsError()
            }
        }
    }

    private fun returnToLoginButton() {
        binding.tvInformation.setOnClickListener {
            returnToLogin()
        }
    }

    private fun goToLogin(user: User) {
        val bundle = bundleOf("USER_KEY" to user)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_registerFragment_to_loginFragment, bundle)
    }

    private fun returnToLogin() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun getData(): User? {
        val username = binding.etUserName.text.toString()
        val userEmail = binding.etUserEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val passwordConfirm = binding.etPasswordConfirmation.text.toString()

        if (username.isNotEmpty() && userEmail.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()) {
            return User(
                name = username,
                email = userEmail,
                password = password,
                passwordConfirmation = passwordConfirm
            )
        }
        return null
    }

    private fun initObservers() {
        viewModel.registerState.observe(this.viewLifecycleOwner) {
            goToLogin(it)
        }

        viewModel.errorState.observe(this.viewLifecycleOwner) {
            if (it == "Email inválido")
                binding.etUserEmail.error = "Email inválido"
        }
    }

    private fun showFieldsError() {
        if (binding.etUserName.text.toString().isEmpty()) {
            binding.etUserName.error = "Campo obrigatório: Nome"
        }

        if (binding.etUserEmail.text.toString().isEmpty()) {
            binding.etUserEmail.error = "Campo obrigatório: Email"
        }

        if (binding.etPassword.text.toString().isEmpty()) {
            binding.etPassword.error = "Campo obrigatório: Senha"
        }

        if (binding.etPasswordConfirmation.text.toString().isEmpty()) {
            binding.etPasswordConfirmation.error = "Campo obrigatório: Confirmação da senha"
        }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

}