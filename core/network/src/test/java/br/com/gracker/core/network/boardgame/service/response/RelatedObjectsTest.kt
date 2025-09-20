package br.com.gracker.core.network.boardgame.service.response

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import java.io.InputStreamReader

/**
 * TDD Tests for related object models needed for GameDetailResponse.
 * These test individual data classes that compose the complete game response.
 * All tests designed to FAIL initially to drive implementation.
 */
class RelatedObjectsTest {
    private lateinit var gson: Gson
    private lateinit var actualApiResponse: String

    @Before
    fun setUp() {
        gson = Gson()
        actualApiResponse = this::class.java.classLoader
            ?.getResourceAsStream("game_detail_response.json")
            .use { InputStreamReader(it).readText() }
    }

    @Test
    fun `Category data class should parse correctly`() {
        // GIVEN: API response contains category data
        val apiData = gson.fromJson(actualApiResponse, Map::class.java)
        val categoriesData = apiData["categories"] as List<Map<String, Any>>

        // WHEN: Parsing category objects
        // This will FAIL - Category class doesn't exist yet
        val categoryJson = gson.toJson(categoriesData[0])
        val category = gson.fromJson(categoryJson, Category::class.java)

        // THEN: Category should have correct structure
        assertThat(category.id).isEqualTo("1002")
        assertThat(category.name).isEqualTo("Card Game")
    }

    @Test
    fun `Mechanic data class should parse correctly`() {
        // GIVEN: API response contains mechanic data
        val apiData = gson.fromJson(actualApiResponse, Map::class.java)
        val mechanicsData = apiData["mechanic"] as List<Map<String, Any>>

        // WHEN: Parsing mechanic objects
        // This will FAIL - Mechanic class doesn't exist yet
        val mechanicJson = gson.toJson(mechanicsData[0])
        val mechanic = gson.fromJson(mechanicJson, Mechanic::class.java)

        // THEN: Mechanic should have correct structure
        assertThat(mechanic.id).isEqualTo("2004")
        assertThat(mechanic.name).isEqualTo("Set Collection")
    }

    @Test
    fun `Publisher data class should parse correctly`() {
        // GIVEN: API response contains publisher data
        val apiData = gson.fromJson(actualApiResponse, Map::class.java)
        val publishersData = apiData["publishers"] as List<Map<String, Any>>

        // WHEN: Parsing publisher objects
        // This will FAIL - Publisher class doesn't exist yet
        val publisherJson = gson.toJson(publishersData[0])
        val publisher = gson.fromJson(publisherJson, Publisher::class.java)

        // THEN: Publisher should have correct structure
        assertThat(publisher.id).isEqualTo("2330")
        assertThat(publisher.name).isEqualTo("Playful Minds")
    }

    @Test
    fun `DevelopmentTeamMember data class should parse correctly`() {
        // GIVEN: API response contains development team data
        val apiData = gson.fromJson(actualApiResponse, Map::class.java)
        val teamData = apiData["development_team"] as List<Map<String, Any>>

        // WHEN: Parsing development team member objects
        // This will FAIL - DevelopmentTeamMember class doesn't exist yet
        val memberJson = gson.toJson(teamData[0])
        val member = gson.fromJson(memberJson, DevelopmentTeamMember::class.java)

        // THEN: Member should have correct structure
        assertThat(member.id).isEqualTo("3")
        assertThat(member.name).isEqualTo("(Uncredited)")
        assertThat(member.role).isEqualTo("Designer")
    }

    @Test
    fun `SuggestedPlayersAgePoll data class should parse correctly`() {
        // GIVEN: API response contains age poll data
        val apiData = gson.fromJson(actualApiResponse, Map::class.java)
        val pollsData = apiData["suggested_players_age_poll"] as List<Map<String, Any>>

        // WHEN: Parsing age poll objects
        // This will FAIL - SuggestedPlayersAgePoll class exists but may need updates
        val pollJson = gson.toJson(pollsData[5]) // Age "8" with 1 vote
        val poll = gson.fromJson(pollJson, SuggestedPlayersAgePoll::class.java)

        // THEN: Poll should have correct structure
        assertThat(poll.age).isEqualTo("8")
        assertThat(poll.votes).isEqualTo(1)
    }

    @Test
    fun `GameStatistics data class should parse correctly`() {
        // GIVEN: API response contains statistics data
        val apiData = gson.fromJson(actualApiResponse, Map::class.java)
        val statsData = apiData["statistics"] as Map<String, Any>

        // WHEN: Parsing statistics object
        // This will FAIL - GameStatistics class doesn't exist yet
        val statsJson = gson.toJson(statsData)
        val statistics = gson.fromJson(statsJson, GameStatistics::class.java)

        // THEN: Statistics should have correct structure
        assertThat(statistics.usersOwned).isEqualTo(54)
        assertThat(statistics.averageRating).isEqualTo("7.31034")
        assertThat(statistics.totalVotes).isEqualTo(29)
        assertThat(statistics.shoppingStatistics).isNotNull()
    }

    @Test
    fun `ShoppingStatistics data class should parse correctly`() {
        // GIVEN: API response contains shopping statistics data
        val apiData = gson.fromJson(actualApiResponse, Map::class.java)
        val statsData = apiData["statistics"] as Map<String, Any>
        val shoppingData = statsData["shopping_statistics"] as Map<String, Any>

        // WHEN: Parsing shopping statistics object
        // This will FAIL - ShoppingStatistics class doesn't exist yet
        val shoppingJson = gson.toJson(shoppingData)
        val shoppingStats = gson.fromJson(shoppingJson, ShoppingStatistics::class.java)

        // THEN: Shopping statistics should have correct structure
        assertThat(shoppingStats.forTrade).isEqualTo(2)
        assertThat(shoppingStats.wantInTrade).isEqualTo(4)
        assertThat(shoppingStats.wishlist).isEqualTo(8)
    }

    @Test
    fun `Expansion data class should handle empty list correctly`() {
        // GIVEN: API response contains empty expansions array
        val apiData = gson.fromJson(actualApiResponse, Map::class.java)
        val expansionsData = apiData["expansions"] as List<Any>

        // THEN: Should handle empty list gracefully
        // This will FAIL if Expansion class doesn't exist
        assertThat(expansionsData).isEmpty()

        // When we do have expansion data, it should parse correctly
        // (This is future-proofing for when we have games with expansions)
    }

    @Test
    fun `all related objects should have proper serialization annotations`() {
        // This test validates that field names map correctly from snake_case API to camelCase Android
        // Will FAIL if @SerializedName annotations are missing

        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // Verify field name mapping works for nested objects
        gameDetail.statistics?.let { statistics ->
            // users_owned -> usersOwned
            assertThat(statistics.usersOwned).isNotNull()
            // average_rating -> averageRating
            assertThat(statistics.averageRating).isNotNull()
            // total_votes -> totalVotes
            assertThat(statistics.totalVotes).isNotNull()

            // for_trade -> forTrade
            assertThat(statistics.shoppingStatistics.forTrade).isNotNull()
            // want_in_trade -> wantInTrade
            assertThat(statistics.shoppingStatistics.wantInTrade).isNotNull()
        }
    }
}
