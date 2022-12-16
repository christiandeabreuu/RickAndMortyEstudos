package com.example.myappbancodados.rickandmorty.data.model


import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("info")
    val info: LocationInfo,
    @SerializedName("results")
    val listResults: List<LocationResult>
)