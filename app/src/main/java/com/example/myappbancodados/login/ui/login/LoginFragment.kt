package com.example.myappbancodados.login.ui.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.myappbancodados.R
import com.example.myappbancodados.databinding.FragmentLoginBinding
import com.example.myappbancodados.login.data.local.model.User
import com.example.myappbancodados.viewstate.ViewState

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerButton()
        loginButton()
        initObservers()
    }

    private fun initObservers() {
        viewModel.loginState.observe(this.viewLifecycleOwner) {

            goToHome(it)
        }
        viewModel.errorState.observe(this.viewLifecycleOwner) {
            hideKeyboard()
            Toast.makeText(context, "LOGIN_PASSWORD_INCORRECT", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToHome(user: User) {
        val bundle = bundleOf("USER_KEY" to user)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_loginFragment_to_initialActivity, bundle) // , bundle
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun registerButton() {
        binding.bvRegisterNow.setOnClickListener {
            goToRegister()
        }
    }

    private fun goToRegister() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun loginButton() {
        binding.bvLogin.setOnClickListener {
            hideKeyboard()
            if (validateField()) {
                val user = getDataUser()
                viewModel.validateDataUser(user)
            }
        }
    }

    private fun getDataUser(): User {
        return User(
            email = binding.etUserEmail.text.toString(),
            password = binding.etPassword.text.toString()
        )
    }

    private fun validateField(): Boolean {
        return when {
            (binding.etUserEmail.text.toString().isEmpty() && binding.etPassword.text.toString()
                .isEmpty()) -> {
                binding.etUserEmail.error = "Campo obrigatório: Email"
                binding.etPassword.error = "Campo obrigatório: Senha"
                false
            }
            binding.etUserEmail.text.toString().isEmpty() -> {
                binding.etUserEmail.error = "Campo obrigatório: Email"
                false
            }
            binding.etPassword.text.toString().isEmpty() -> {
                binding.etPassword.error = "Campo obrigatório: Senha"
                false
            }
            else -> true
        }
    }
}
