package br.com.gracker.core.network.boardgame.service.response

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import java.io.InputStreamReader

/**
 * TDD Tests for GameDetailResponse data model synchronization with backend API.
 * These tests are designed to FAIL initially to drive proper implementation.
 *
 * Backend API: https://gracker.com.br/game/{id}
 * Backend Model: br.com.gracker.game.model.Game (Kotlin/Ktor)
 */
class GameDetailResponseTest {
    private lateinit var gson: Gson
    private lateinit var actualApiResponse: String

    @Before
    fun setUp() {
        gson = Gson()
        // Load real API response from test resources
        actualApiResponse = this::class.java.classLoader
            ?.getResourceAsStream("game_detail_response.json")!!
            .use { InputStreamReader(it).readText() }
    }

    @Test
    fun `should parse all basic game fields from API response`() {
        // GIVEN: Real API response from gracker.com.br/game/123
        // WHEN: Parsing with current GameDetailResponse
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: All basic fields should be present and correctly typed
        assertThat(gameDetail.id).isEqualTo("123")
        assertThat(gameDetail.name).isEqualTo("Green Thumb Cards")
        assertThat(gameDetail.description).contains("competing gardeners")
        assertThat(gameDetail.yearPublished).isEqualTo(1996)
        assertThat(gameDetail.minimumPlayers).isEqualTo(2)
        assertThat(gameDetail.maximumPlayers).isEqualTo(5)

        // These fields are missing in current model - tests will FAIL
        assertThat(gameDetail.minimumPlayingTime).isEqualTo(45)
        assertThat(gameDetail.maximumPlayingTime).isEqualTo(45)
        assertThat(gameDetail.minimumPlayerAge).isEqualTo(7)
    }

    @Test
    fun `should parse suggested players age poll from API response`() {
        // GIVEN: API response contains age poll data
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: Age poll should be parsed correctly
        // This will FAIL - current model missing suggestedPlayersAgePoll
        assertThat(gameDetail.suggestedPlayersAgePoll).isNotEmpty()
        assertThat(gameDetail.suggestedPlayersAgePoll.size).isEqualTo(12)

        val firstPoll = gameDetail.suggestedPlayersAgePoll[0]
        assertThat(firstPoll.age).isEqualTo("2")
        assertThat(firstPoll.votes).isEqualTo(0)

        // Check poll with actual votes
        val pollWithVotes = gameDetail.suggestedPlayersAgePoll.find { it.age == "8" }
        assertThat(pollWithVotes?.votes).isEqualTo(1)
    }

    @Test
    fun `should parse categories from API response`() {
        // GIVEN: API response contains categories data
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: Categories should be parsed correctly
        // This will FAIL - current model missing categories
        assertThat(gameDetail.categories).isNotNull()
        assertThat(gameDetail.categories!!.size).isEqualTo(1)

        val category = gameDetail.categories!![0]
        assertThat(category.id).isEqualTo("1002")
        assertThat(category.name).isEqualTo("Card Game")
    }

    @Test
    fun `should parse mechanics from API response`() {
        // GIVEN: API response contains mechanics data
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: Mechanics should be parsed correctly
        // This will FAIL - current model missing mechanic field
        assertThat(gameDetail.mechanic).isNotNull()
        assertThat(gameDetail.mechanic!!.size).isEqualTo(2)

        val setCollection = gameDetail.mechanic!!.find { it.name == "Set Collection" }
        assertThat(setCollection?.id).isEqualTo("2004")

        val takeThat = gameDetail.mechanic!!.find { it.name == "Take That" }
        assertThat(takeThat?.id).isEqualTo("2686")
    }

    @Test
    fun `should parse publishers from API response`() {
        // GIVEN: API response contains publishers data
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: Publishers should be parsed correctly
        // This will FAIL - current model missing publishers
        assertThat(gameDetail.publishers).isNotNull()
        assertThat(gameDetail.publishers!!.size).isEqualTo(1)

        val publisher = gameDetail.publishers!![0]
        assertThat(publisher.id).isEqualTo("2330")
        assertThat(publisher.name).isEqualTo("Playful Minds")
    }

    @Test
    fun `should parse development team from API response`() {
        // GIVEN: API response contains development team data
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: Development team should be parsed correctly
        // This will FAIL - current model missing developmentTeam
        assertThat(gameDetail.developmentTeam).isNotNull()
        assertThat(gameDetail.developmentTeam!!.size).isEqualTo(2)

        val designer = gameDetail.developmentTeam!!.find { it.role == "Designer" }
        assertThat(designer?.name).isEqualTo("(Uncredited)")
        assertThat(designer?.id).isEqualTo("3")

        val artist = gameDetail.developmentTeam!!.find { it.role == "Artist" }
        assertThat(artist?.name).isEqualTo("(Uncredited)")
        assertThat(artist?.id).isEqualTo("3")
    }

    @Test
    fun `should parse expansions from API response`() {
        // GIVEN: API response contains expansions data (empty array in this case)
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: Expansions should be parsed correctly
        // This will FAIL - current model missing expansions
        assertThat(gameDetail.expansions).isNotNull()
        assertThat(gameDetail.expansions).isEmpty()
    }

    @Test
    fun `should parse statistics from API response`() {
        // GIVEN: API response contains statistics data
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: Statistics should be parsed correctly
        // This will FAIL - current model missing statistics
        assertThat(gameDetail.statistics).isNotNull()
        assertThat(gameDetail.statistics!!.usersOwned).isEqualTo(54)
        assertThat(gameDetail.statistics!!.averageRating).isEqualTo("7.31034")
        assertThat(gameDetail.statistics!!.totalVotes).isEqualTo(29)

        // Shopping statistics
        assertThat(gameDetail.statistics!!.shoppingStatistics).isNotNull()
        assertThat(gameDetail.statistics!!.shoppingStatistics.forTrade).isEqualTo(2)
        assertThat(gameDetail.statistics!!.shoppingStatistics.wantInTrade).isEqualTo(4)
        assertThat(gameDetail.statistics!!.shoppingStatistics.wishlist).isEqualTo(8)
    }

    @Test
    fun `should handle field name mapping correctly`() {
        // GIVEN: API response uses snake_case field names
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: Should map snake_case to camelCase correctly
        // This may FAIL if @SerializedName annotations are missing
        assertThat(gameDetail.yearPublished).isEqualTo(1996) // year_published -> yearPublished
        assertThat(gameDetail.minimumPlayers).isEqualTo(2) // minimum_players -> minimumPlayers
        assertThat(gameDetail.maximumPlayers).isEqualTo(5) // maximum_players -> maximumPlayers
    }

    @Test
    fun `should match backend Game model structure completely`() {
        // GIVEN: API response from backend Game model
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: Android model should have all fields from backend br.com.gracker.game.model.Game
        // This will FAIL - current model is incomplete

        // Verify all required fields are present (this will fail for missing fields)
        assertThat(gameDetail::class.java.declaredFields.map { it.name }).containsAtLeast(
            "id",
            "name",
            "description",
            "yearPublished",
            "minimumPlayers",
            "maximumPlayers",
            "suggestedPlayersAgePoll",
            "minimumPlayingTime",
            "maximumPlayingTime",
            "minimumPlayerAge",
            "categories",
            "mechanic",
            "expansions",
            "publishers",
            "developmentTeam",
            "statistics",
        )
    }
}
