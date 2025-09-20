package br.com.gracker.core.network.boardgame.domain

import java.time.Year

data class GameDetail(
    val id: String,
    val name: String,
    val description: String,
    val yearPublished: Year,
    val minimumPlayers: Int,
    val maximumPlayers: Int,
    val minimumPlayingTime: Int?,
    val maximumPlayingTime: Int?,
    val minimumPlayerAge: Int?,
    val suggestedPlayersAgePoll: List<SuggestedPlayersAgePoll>,
    val categories: List<Category>,
    val mechanics: List<Mechanic>,
    val expansions: List<Expansion>,
    val publishers: List<Publisher>,
    val developmentTeam: List<DevelopmentTeamMember>,
    val statistics: GameStatistics?,
) {
    val averageRating: Double?
        get() = statistics?.averageRating

    val hasRating: Boolean
        get() = statistics?.averageRating != null && statistics.averageRating > 0.0
}
