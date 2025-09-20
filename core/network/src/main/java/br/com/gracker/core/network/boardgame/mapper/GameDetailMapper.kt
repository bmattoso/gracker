package br.com.gracker.core.network.boardgame.mapper

import br.com.gracker.core.network.boardgame.domain.Category
import br.com.gracker.core.network.boardgame.domain.DevelopmentTeamMember
import br.com.gracker.core.network.boardgame.domain.Expansion
import br.com.gracker.core.network.boardgame.domain.GameDetail
import br.com.gracker.core.network.boardgame.domain.GameStatistics
import br.com.gracker.core.network.boardgame.domain.Mechanic
import br.com.gracker.core.network.boardgame.domain.Publisher
import br.com.gracker.core.network.boardgame.domain.ShoppingStatistics
import br.com.gracker.core.network.boardgame.domain.SuggestedPlayersAgePoll
import br.com.gracker.core.network.boardgame.service.response.CategoryResponse
import br.com.gracker.core.network.boardgame.service.response.DevelopmentTeamMemberResponse
import br.com.gracker.core.network.boardgame.service.response.ExpansionResponse
import br.com.gracker.core.network.boardgame.service.response.GameDetailResponse
import br.com.gracker.core.network.boardgame.service.response.GameStatisticsResponse
import br.com.gracker.core.network.boardgame.service.response.MechanicResponse
import br.com.gracker.core.network.boardgame.service.response.PublisherResponse
import br.com.gracker.core.network.boardgame.service.response.ShoppingStatisticsResponse
import br.com.gracker.core.network.boardgame.service.response.SuggestedPlayersAgePollResponse
import java.time.Year

fun GameDetailResponse.toDomain(): GameDetail {
    return GameDetail(
        id = id,
        name = name,
        description = description,
        yearPublished = Year.of(yearPublished),
        minimumPlayers = minimumPlayers,
        maximumPlayers = maximumPlayers,
        minimumPlayingTime = minimumPlayingTime,
        maximumPlayingTime = maximumPlayingTime,
        minimumPlayerAge = minimumPlayerAge,
        suggestedPlayersAgePoll = suggestedPlayersAgePoll.map { it.toDomain() },
        categories = categories?.map { it.toDomain() } ?: emptyList(),
        mechanics = mechanic?.map { it.toDomain() } ?: emptyList(),
        expansions = expansions?.map { it.toDomain() } ?: emptyList(),
        publishers = publishers?.map { it.toDomain() } ?: emptyList(),
        developmentTeam = developmentTeam?.map { it.toDomain() } ?: emptyList(),
        statistics = statistics?.toDomain(),
    )
}

fun CategoryResponse.toDomain(): Category {
    return Category(
        id = id,
        name = name,
    )
}

fun MechanicResponse.toDomain(): Mechanic {
    return Mechanic(
        id = id,
        name = name,
    )
}

fun PublisherResponse.toDomain(): Publisher {
    return Publisher(
        id = id,
        name = name,
    )
}

fun ExpansionResponse.toDomain(): Expansion {
    return Expansion(
        id = id,
        name = name,
    )
}

fun DevelopmentTeamMemberResponse.toDomain(): DevelopmentTeamMember {
    return DevelopmentTeamMember(
        id = id,
        name = name,
        role = role,
    )
}

fun SuggestedPlayersAgePollResponse.toDomain(): SuggestedPlayersAgePoll {
    return SuggestedPlayersAgePoll(
        age = age,
        votes = votes,
    )
}

fun GameStatisticsResponse.toDomain(): GameStatistics {
    val rating = try {
        averageRating.toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }

    return GameStatistics(
        usersOwned = usersOwned,
        averageRating = rating,
        totalVotes = totalVotes,
        shoppingStatistics = shoppingStatistics.toDomain(),
    )
}

fun ShoppingStatisticsResponse.toDomain(): ShoppingStatistics {
    return ShoppingStatistics(
        forTrade = forTrade,
        wantInTrade = wantInTrade,
        wishlist = wishlist,
    )
}
