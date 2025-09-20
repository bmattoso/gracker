package br.com.gracker.core.network.boardgame.service.response

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.Before
import org.junit.Test
import java.io.InputStreamReader

/**
 * TDD Tests for JSON serialization and field mapping between API and Android models.
 * Tests proper handling of snake_case -> camelCase conversion and data type compatibility.
 * All tests designed to FAIL initially to drive proper @SerializedName annotation usage.
 */
class SerializationTest {
    private lateinit var gson: Gson
    private lateinit var actualApiResponse: String

    @Before
    fun setUp() {
        gson = GsonBuilder()
            .setLenient()
            .create()

        actualApiResponse = this::class.java.classLoader
            .getResourceAsStream("game_detail_response.json")!!
            .use { InputStreamReader(it).readText() }
    }

    @Test
    fun `should handle snake_case to camelCase field mapping`() {
        // GIVEN: API response uses snake_case field names
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: Should map correctly using @SerializedName annotations
        // These will FAIL if annotations are missing

        // year_published -> yearPublished
        assertThat(gameDetail.yearPublished).isEqualTo(1996)

        // minimum_players -> minimumPlayers
        assertThat(gameDetail.minimumPlayers).isEqualTo(2)

        // maximum_players -> maximumPlayers
        assertThat(gameDetail.maximumPlayers).isEqualTo(5)

        // minimum_playing_time -> minimumPlayingTime
        assertThat(gameDetail.minimumPlayingTime).isEqualTo(45)

        // maximum_playing_time -> maximumPlayingTime
        assertThat(gameDetail.maximumPlayingTime).isEqualTo(45)

        // minimum_player_age -> minimumPlayerAge
        assertThat(gameDetail.minimumPlayerAge).isEqualTo(7)

        // suggested_players_age_poll -> suggestedPlayersAgePoll
        assertThat(gameDetail.suggestedPlayersAgePoll).isNotEmpty()

        // development_team -> developmentTeam
        assertThat(gameDetail.developmentTeam).isNotEmpty()
    }

    @Test
    fun `should handle data type conversion correctly`() {
        // GIVEN: API response with various data types
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: Should convert types appropriately
        // Backend uses Long, Android may use Int for some fields

        // These should work with proper type definitions
        assertThat(gameDetail.yearPublished).isEqualTo(1996)
        assertThat(gameDetail.minimumPlayers).isEqualTo(2)
        assertThat(gameDetail.maximumPlayers).isEqualTo(5)

        // Check that nullable fields handle null correctly
        if (gameDetail.minimumPlayingTime != null) {
            assertThat(gameDetail.minimumPlayingTime).isGreaterThan(0)
        }
    }

    @Test
    fun `should serialize and deserialize consistently`() {
        // GIVEN: Parsed GameDetailResponse object
        val originalGame = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // WHEN: Serializing back to JSON and parsing again
        val serializedJson = gson.toJson(originalGame)
        val reparsedGame = gson.fromJson(serializedJson, GameDetailResponse::class.java)

        // THEN: Should maintain data integrity
        // This will FAIL if serialization annotations are inconsistent
        assertThat(reparsedGame.id).isEqualTo(originalGame.id)
        assertThat(reparsedGame.name).isEqualTo(originalGame.name)
        assertThat(reparsedGame.yearPublished).isEqualTo(originalGame.yearPublished)
        assertThat(reparsedGame.minimumPlayers).isEqualTo(originalGame.minimumPlayers)
        assertThat(reparsedGame.maximumPlayers).isEqualTo(originalGame.maximumPlayers)
    }

    @Test
    fun `should handle missing optional fields gracefully`() {
        // GIVEN: JSON with some fields missing
        val partialJson = """
        {
            "id": "123",
            "name": "Test Game",
            "description": "Test description",
            "year_published": 2020,
            "minimum_players": 1,
            "maximum_players": 4
        }
        """.trimIndent()

        // WHEN: Parsing incomplete JSON
        val gameDetail = gson.fromJson(partialJson, GameDetailResponse::class.java)

        // THEN: Should handle missing fields gracefully
        // This will FAIL if nullable fields aren't properly defined
        assertThat(gameDetail.id).isEqualTo("123")
        assertThat(gameDetail.name).isEqualTo("Test Game")
        assertThat(gameDetail.yearPublished).isEqualTo(2020)

        // These should be null when not provided in JSON
        assertThat(gameDetail.categories).isNull() // Should be null when missing
        assertThat(gameDetail.mechanic).isNull() // Should be null when missing
        assertThat(gameDetail.publishers).isNull() // Should be null when missing
    }

    @Test
    fun `should match exact field names from backend API`() {
        // GIVEN: Raw API response data
        val rawData = gson.fromJson(actualApiResponse, Map::class.java)

        // THEN: Should have all expected API field names
        // This validates we're not missing any fields from the API
        val expectedApiFields = listOf(
            "id",
            "name",
            "description",
            "year_published",
            "minimum_players",
            "maximum_players",
            "suggested_players_age_poll",
            "minimum_playing_time",
            "maximum_playing_time",
            "minimum_player_age",
            "categories",
            "mechanic",
            "expansions",
            "publishers",
            "development_team",
            "statistics",
        )

        expectedApiFields.forEach { fieldName ->
            assertThat(rawData).containsKey(fieldName)
        }
    }

    @Test
    fun `should validate complete model compatibility with backend`() {
        // GIVEN: Real API response
        val gameDetail = gson.fromJson(actualApiResponse, GameDetailResponse::class.java)

        // THEN: Android model should be fully compatible with backend br.com.gracker.game.model.Game
        // This is the ultimate integration test - will FAIL until model is complete

        // Verify no parsing errors occurred
        assertThat(gameDetail).isNotNull()

        // Verify all core fields are present
        assertThat(gameDetail.id).isNotNull()
        assertThat(gameDetail.name).isNotNull()
        assertThat(gameDetail.description).isNotNull()
        assertThat(gameDetail.yearPublished).isNotNull()
        assertThat(gameDetail.minimumPlayers).isNotNull()
        assertThat(gameDetail.maximumPlayers).isNotNull()

        // Verify complex objects are present
        assertThat(gameDetail.suggestedPlayersAgePoll).isNotNull()
        assertThat(gameDetail.categories).isNotNull()
        assertThat(gameDetail.mechanic).isNotNull()
        assertThat(gameDetail.publishers).isNotNull()
        assertThat(gameDetail.developmentTeam).isNotNull()
        assertThat(gameDetail.statistics).isNotNull()

        // Verify no fields are unexpectedly null
        assertThat(gameDetail.toString()).doesNotContain("null")
    }
}
