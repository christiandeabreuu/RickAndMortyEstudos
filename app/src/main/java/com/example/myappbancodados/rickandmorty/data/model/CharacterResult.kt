package com.example.myappbancodados.rickandmorty.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResult(
    @SerializedName("created")
    val created: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
): Parcelable

//@SerializedName("origin")
//val origin: Origin,
//@SerializedName("location")
//val location: Location,