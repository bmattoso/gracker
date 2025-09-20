package br.com.gracker.core.network.boardgame.domain

data class GameStatistics(
    val usersOwned: Int,
    val averageRating: Double,
    val totalVotes: Int,
    val shoppingStatistics: ShoppingStatistics,
)
