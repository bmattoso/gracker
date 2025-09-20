package br.com.gracker.core.network.boardgame.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mechanic(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
)
