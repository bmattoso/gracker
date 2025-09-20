package br.com.gracker.core.network.boardgame.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("year_published")
    val yearPublished: Int,
    @SerialName("minimum_players")
    val minimumPlayers: Int,
    @SerialName("maximum_players")
    val maximumPlayers: Int,
    @SerialName("minimum_playing_time")
    val minimumPlayingTime: Int?,
    @SerialName("maximum_playing_time")
    val maximumPlayingTime: Int?,
    @SerialName("minimum_player_age")
    val minimumPlayerAge: Int?,
    @SerialName("suggested_players_age_poll")
    val suggestedPlayersAgePoll: List<SuggestedPlayersAgePollResponse> = emptyList(),
    @SerialName("categories")
    val categories: List<CategoryResponse>? = null,
    @SerialName("mechanic")
    val mechanic: List<MechanicResponse>? = null,
    @SerialName("expansions")
    val expansions: List<ExpansionResponse>? = null,
    @SerialName("publishers")
    val publishers: List<PublisherResponse>? = null,
    @SerialName("development_team")
    val developmentTeam: List<DevelopmentTeamMemberResponse>? = null,
    @SerialName("statistics")
    val statistics: GameStatisticsResponse?,
)
