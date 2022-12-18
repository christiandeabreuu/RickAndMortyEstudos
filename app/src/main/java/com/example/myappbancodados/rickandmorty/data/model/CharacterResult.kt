package com.example.myappbancodados.rickandmorty.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "character")
@Parcelize
data class CharacterResult(
    @SerializedName("created")
    val created: String,
//    @SerializedName("episode")
//    val episode: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    @PrimaryKey
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