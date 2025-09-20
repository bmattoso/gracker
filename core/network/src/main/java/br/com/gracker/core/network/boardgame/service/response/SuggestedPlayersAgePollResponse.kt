package br.com.gracker.core.network.boardgame.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuggestedPlayersAgePollResponse(
    @SerialName("age")
    val age: String,
    @SerialName("votes")
    val votes: Int,
)
