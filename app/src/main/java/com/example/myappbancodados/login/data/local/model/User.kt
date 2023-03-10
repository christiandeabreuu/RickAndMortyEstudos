package com.example.myappbancodados.login.data.local.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var passwordConfirmation: String = "",
    var points: Int = 0,
    var level: Int = 0
) : Parcelable