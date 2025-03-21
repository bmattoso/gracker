package br.com.gracker.core.network.boardgame.service.response

data class GameDetailResponse(
    val id: String,
    val name: String,
    val description: String,
    val yearPublished: Int,
    val minimumPlayers: Int,
    val maximumPlayers: Int,
    val minimumPlayingTime: Int?,
    val maximumPlayingTime: Int?,
    val minimumPlayerAge: Int?,
    var suggestedPlayersAgePoll: List<SuggestedPlayersAgePoll>,
    val categories: List<Categories>,
    val mechanic: List<Mechanic>,
    val expansions: List<Expansions>,
    val publishers: List<Publishers>,
    val developmentTeam: List<DevelopmentTeam>,
    val statistics: Statistics?,
)
