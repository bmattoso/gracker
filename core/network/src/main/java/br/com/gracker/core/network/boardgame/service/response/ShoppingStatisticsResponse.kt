package br.com.gracker.core.network.boardgame.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShoppingStatisticsResponse(
    @SerialName("for_trade")
    val forTrade: Int,
    @SerialName("want_in_trade")
    val wantInTrade: Int,
    @SerialName("wishlist")
    val wishlist: Int,
)
