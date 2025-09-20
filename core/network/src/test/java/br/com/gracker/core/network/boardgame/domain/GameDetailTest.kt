package br.com.gracker.core.network.boardgame.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.Year

class GameDetailTest {
    @Test
    fun `should create GameDetail with proper domain types`() {
        val gameDetail = GameDetail(
            id = "123",
            name = "Green Thumb Cards",
            description = "A card game about gardening",
            yearPublished = Year.of(1996),
            minimumPlayers = 2,
            maximumPlayers = 5,
            minimumPlayingTime = 45,
            maximumPlayingTime = 45,
            minimumPlayerAge = 7,
            suggestedPlayersAgePoll = listOf(
                SuggestedPlayersAgePoll("8", 1),
            ),
            categories = listOf(
                Category("1002", "Card Game"),
            ),
            mechanics = listOf(
                Mechanic("2004", "Set Collection"),
            ),
            expansions = emptyList(),
            publishers = listOf(
                Publisher("2330", "Playful Minds"),
            ),
            developmentTeam = listOf(
                DevelopmentTeamMember("3", "(Uncredited)", "Designer"),
            ),
            statistics = GameStatistics(
                usersOwned = 54,
                averageRating = 7.31034,
                totalVotes = 29,
                shoppingStatistics = ShoppingStatistics(
                    forTrade = 2,
                    wantInTrade = 4,
                    wishlist = 8,
                ),
            ),
        )

        assertThat(gameDetail.id).isEqualTo("123")
        assertThat(gameDetail.yearPublished).isEqualTo(Year.of(1996))
        assertThat(gameDetail.averageRating).isEqualTo(7.31034)
        assertThat(gameDetail.categories).hasSize(1)
        assertThat(gameDetail.mechanics).hasSize(1)
    }

    @Test
    fun `should handle empty collections gracefully`() {
        val gameDetail = GameDetail(
            id = "456",
            name = "Simple Game",
            description = "Basic game",
            yearPublished = Year.of(2020),
            minimumPlayers = 1,
            maximumPlayers = 4,
            minimumPlayingTime = 30,
            maximumPlayingTime = 60,
            minimumPlayerAge = 10,
            suggestedPlayersAgePoll = emptyList(),
            categories = emptyList(),
            mechanics = emptyList(),
            expansions = emptyList(),
            publishers = emptyList(),
            developmentTeam = emptyList(),
            statistics = null,
        )

        assertThat(gameDetail.categories).isEmpty()
        assertThat(gameDetail.mechanics).isEmpty()
        assertThat(gameDetail.publishers).isEmpty()
        assertThat(gameDetail.statistics).isNull()
    }

    @Test
    fun `should validate proper data types`() {
        val gameDetail = GameDetail(
            id = "789",
            name = "Test Game",
            description = "Test",
            yearPublished = Year.of(2024),
            minimumPlayers = 2,
            maximumPlayers = 6,
            minimumPlayingTime = 15,
            maximumPlayingTime = 90,
            minimumPlayerAge = 8,
            suggestedPlayersAgePoll = emptyList(),
            categories = emptyList(),
            mechanics = emptyList(),
            expansions = emptyList(),
            publishers = emptyList(),
            developmentTeam = emptyList(),
            statistics = GameStatistics(
                usersOwned = 100,
                averageRating = 8.5,
                totalVotes = 50,
                shoppingStatistics = ShoppingStatistics(0, 0, 10),
            ),
        )

        assertThat(gameDetail.yearPublished).isInstanceOf(Year::class.java)
        assertThat(gameDetail.statistics?.averageRating).isInstanceOf(Double::class.java)
        assertThat(gameDetail.minimumPlayers).isInstanceOf(Int::class.java)
    }

    @Test
    fun `should provide computed property for average rating`() {
        val statistics = GameStatistics(
            usersOwned = 54,
            averageRating = 7.31034,
            totalVotes = 29,
            shoppingStatistics = ShoppingStatistics(2, 4, 8),
        )

        val gameDetail = GameDetail(
            id = "123",
            name = "Test",
            description = "Test",
            yearPublished = Year.of(2020),
            minimumPlayers = 1,
            maximumPlayers = 4,
            minimumPlayingTime = 30,
            maximumPlayingTime = 60,
            minimumPlayerAge = 10,
            suggestedPlayersAgePoll = emptyList(),
            categories = emptyList(),
            mechanics = emptyList(),
            expansions = emptyList(),
            publishers = emptyList(),
            developmentTeam = emptyList(),
            statistics = statistics,
        )

        assertThat(gameDetail.averageRating).isEqualTo(7.31034)
        assertThat(gameDetail.hasRating).isTrue()
    }

    @Test
    fun `should handle null statistics for computed properties`() {
        val gameDetail = GameDetail(
            id = "123",
            name = "Test",
            description = "Test",
            yearPublished = Year.of(2020),
            minimumPlayers = 1,
            maximumPlayers = 4,
            minimumPlayingTime = 30,
            maximumPlayingTime = 60,
            minimumPlayerAge = 10,
            suggestedPlayersAgePoll = emptyList(),
            categories = emptyList(),
            mechanics = emptyList(),
            expansions = emptyList(),
            publishers = emptyList(),
            developmentTeam = emptyList(),
            statistics = null,
        )

        assertThat(gameDetail.averageRating).isNull()
        assertThat(gameDetail.hasRating).isFalse()
    }
}
