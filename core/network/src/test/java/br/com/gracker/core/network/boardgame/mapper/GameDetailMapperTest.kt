package br.com.gracker.core.network.boardgame.mapper

import br.com.gracker.core.network.boardgame.service.response.CategoryResponse
import br.com.gracker.core.network.boardgame.service.response.DevelopmentTeamMemberResponse
import br.com.gracker.core.network.boardgame.service.response.GameDetailResponse
import br.com.gracker.core.network.boardgame.service.response.GameStatisticsResponse
import br.com.gracker.core.network.boardgame.service.response.MechanicResponse
import br.com.gracker.core.network.boardgame.service.response.PublisherResponse
import br.com.gracker.core.network.boardgame.service.response.ShoppingStatisticsResponse
import br.com.gracker.core.network.boardgame.service.response.SuggestedPlayersAgePoll
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.Year

class GameDetailMapperTest {
    @Test
    fun `should map GameDetailResponse to GameDetail domain model`() {
        val response = GameDetailResponse(
            id = "123",
            name = "Green Thumb Cards",
            description = "A card game about gardening",
            yearPublished = 1996,
            minimumPlayers = 2,
            maximumPlayers = 5,
            minimumPlayingTime = 45,
            maximumPlayingTime = 45,
            minimumPlayerAge = 7,
            suggestedPlayersAgePoll = listOf(
                SuggestedPlayersAgePoll("8", 1),
            ),
            categories = listOf(
                CategoryResponse("1002", "Card Game"),
            ),
            mechanic = listOf(
                MechanicResponse("2004", "Set Collection"),
            ),
            expansions = emptyList(),
            publishers = listOf(
                PublisherResponse("2330", "Playful Minds"),
            ),
            developmentTeam = listOf(
                DevelopmentTeamMemberResponse("3", "(Uncredited)", "Designer"),
            ),
            statistics = GameStatisticsResponse(
                usersOwned = 54,
                averageRating = "7.31034",
                totalVotes = 29,
                shoppingStatistics = ShoppingStatisticsResponse(
                    forTrade = 2,
                    wantInTrade = 4,
                    wishlist = 8,
                ),
            ),
        )

        val domain = response.toDomain()

        assertThat(domain.id).isEqualTo("123")
        assertThat(domain.name).isEqualTo("Green Thumb Cards")
        assertThat(domain.yearPublished).isEqualTo(Year.of(1996))
        assertThat(domain.averageRating).isEqualTo(7.31034)
        assertThat(domain.categories).hasSize(1)
        assertThat(domain.categories[0].name).isEqualTo("Card Game")
        assertThat(domain.mechanics).hasSize(1)
        assertThat(domain.mechanics[0].name).isEqualTo("Set Collection")
    }

    @Test
    fun `should handle null statistics in mapping`() {
        val response = GameDetailResponse(
            id = "456",
            name = "Simple Game",
            description = "Basic game",
            yearPublished = 2020,
            minimumPlayers = 1,
            maximumPlayers = 4,
            minimumPlayingTime = 30,
            maximumPlayingTime = 60,
            minimumPlayerAge = 10,
            suggestedPlayersAgePoll = emptyList(),
            categories = null,
            mechanic = null,
            expansions = null,
            publishers = null,
            developmentTeam = null,
            statistics = null,
        )

        val domain = response.toDomain()

        assertThat(domain.statistics).isNull()
        assertThat(domain.categories).isEmpty()
        assertThat(domain.mechanics).isEmpty()
        assertThat(domain.publishers).isEmpty()
        assertThat(domain.hasRating).isFalse()
    }

    @Test
    fun `should parse rating string to double correctly`() {
        val response = GameDetailResponse(
            id = "789",
            name = "Test Game",
            description = "Test",
            yearPublished = 2024,
            minimumPlayers = 2,
            maximumPlayers = 6,
            minimumPlayingTime = 15,
            maximumPlayingTime = 90,
            minimumPlayerAge = 8,
            suggestedPlayersAgePoll = emptyList(),
            categories = emptyList(),
            mechanic = emptyList(),
            expansions = emptyList(),
            publishers = emptyList(),
            developmentTeam = emptyList(),
            statistics = GameStatisticsResponse(
                usersOwned = 100,
                averageRating = "8.50000",
                totalVotes = 50,
                shoppingStatistics = ShoppingStatisticsResponse(0, 0, 10),
            ),
        )

        val domain = response.toDomain()

        assertThat(domain.statistics?.averageRating).isEqualTo(8.5)
        assertThat(domain.averageRating).isEqualTo(8.5)
    }

    @Test
    fun `should handle invalid rating string gracefully`() {
        val response = GameDetailResponse(
            id = "999",
            name = "Invalid Rating Game",
            description = "Test",
            yearPublished = 2024,
            minimumPlayers = 1,
            maximumPlayers = 4,
            minimumPlayingTime = 30,
            maximumPlayingTime = 60,
            minimumPlayerAge = 10,
            suggestedPlayersAgePoll = emptyList(),
            categories = emptyList(),
            mechanic = emptyList(),
            expansions = emptyList(),
            publishers = emptyList(),
            developmentTeam = emptyList(),
            statistics = GameStatisticsResponse(
                usersOwned = 10,
                averageRating = "invalid_rating",
                totalVotes = 5,
                shoppingStatistics = ShoppingStatisticsResponse(1, 2, 3),
            ),
        )

        val domain = response.toDomain()

        assertThat(domain.statistics?.averageRating).isEqualTo(0.0)
        assertThat(domain.hasRating).isFalse()
    }

    @Test
    fun `should map individual response models to domain models`() {
        val categoryResponse = CategoryResponse("123", "Strategy")
        val mechanicResponse = MechanicResponse("456", "Worker Placement")
        val publisherResponse = PublisherResponse("789", "Cool Games Inc")

        val category = categoryResponse.toDomain()
        val mechanic = mechanicResponse.toDomain()
        val publisher = publisherResponse.toDomain()

        assertThat(category.id).isEqualTo("123")
        assertThat(category.name).isEqualTo("Strategy")
        assertThat(mechanic.id).isEqualTo("456")
        assertThat(mechanic.name).isEqualTo("Worker Placement")
        assertThat(publisher.id).isEqualTo("789")
        assertThat(publisher.name).isEqualTo("Cool Games Inc")
    }
}
