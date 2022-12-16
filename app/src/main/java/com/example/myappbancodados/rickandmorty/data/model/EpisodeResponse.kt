package com.example.myappbancodados.rickandmorty.data.model

import com.google.gson.annotations.SerializedName


data class EpisodeResponse(
    @SerializedName("info")
    val info: EpisodeInfo,
    @SerializedName("results")
    val listEpisode: List<EpisodeResult>
)