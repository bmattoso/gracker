package br.com.gracker.core.network.boardgame.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameStatisticsResponse(
    @SerialName("users_owned")
    val usersOwned: Int,
    @SerialName("average_rating")
    val averageRating: String,
    @SerialName("total_votes")
    val totalVotes: Int,
    @SerialName("shopping_statistics")
    val shoppingStatistics: ShoppingStatisticsResponse,
)
