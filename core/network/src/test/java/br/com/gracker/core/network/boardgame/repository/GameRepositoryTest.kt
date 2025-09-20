package br.com.gracker.core.network.boardgame.repository

import br.com.gracker.core.network.boardgame.service.BoardGameService
import br.com.gracker.core.network.boardgame.service.response.CategoryResponse
import br.com.gracker.core.network.boardgame.service.response.DevelopmentTeamMemberResponse
import br.com.gracker.core.network.boardgame.service.response.GameDetailResponse
import br.com.gracker.core.network.boardgame.service.response.GameStatisticsResponse
import br.com.gracker.core.network.boardgame.service.response.MechanicResponse
import br.com.gracker.core.network.boardgame.service.response.PublisherResponse
import br.com.gracker.core.network.boardgame.service.response.ShoppingStatisticsResponse
import br.com.gracker.core.network.boardgame.service.response.SuggestedPlayersAgePoll
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.Year

class GameRepositoryTest {
    private lateinit var mockBoardGameService: BoardGameService
    private lateinit var gameRepository: GameRepository

    @Before
    fun setUp() {
        mockBoardGameService = mockk()
        gameRepository = GameRepositoryImpl(mockBoardGameService)
    }

    @Test
    fun `should return game detail when API call succeeds`() =
        runTest {
            val gameId = "123"
            val mockResponse = GameDetailResponse(
                id = gameId,
                name = "Test Game",
                description = "A test game",
                yearPublished = 2020,
                minimumPlayers = 2,
                maximumPlayers = 4,
                minimumPlayingTime = 30,
                maximumPlayingTime = 60,
                minimumPlayerAge = 10,
                suggestedPlayersAgePoll = emptyList(),
                categories = listOf(CategoryResponse("1", "Strategy")),
                mechanic = listOf(MechanicResponse("2", "Worker Placement")),
                expansions = emptyList(),
                publishers = listOf(PublisherResponse("3", "Test Publisher")),
                developmentTeam = emptyList(),
                statistics = GameStatisticsResponse(
                    usersOwned = 100,
                    averageRating = "8.5",
                    totalVotes = 20,
                    shoppingStatistics = ShoppingStatisticsResponse(1, 2, 3),
                ),
            )

            coEvery { mockBoardGameService.getGameDetailById(gameId) } returns mockResponse

            val result = gameRepository.getGameDetail(gameId)

            assertThat(result.isSuccess).isTrue()
            val gameDetail = result.getOrNull()
            assertThat(gameDetail).isNotNull()
            assertThat(gameDetail?.id).isEqualTo(gameId)
            assertThat(gameDetail?.name).isEqualTo("Test Game")
            assertThat(gameDetail?.yearPublished).isEqualTo(Year.of(2020))
            assertThat(gameDetail?.averageRating).isEqualTo(8.5)

            coVerify { mockBoardGameService.getGameDetailById(gameId) }
        }

    @Test
    fun `should return failure when API call throws exception`() =
        runTest {
            val gameId = "404"
            val exception = RuntimeException("Game not found")

            coEvery { mockBoardGameService.getGameDetailById(gameId) } throws exception

            val result = gameRepository.getGameDetail(gameId)

            assertThat(result.isFailure).isTrue()
            assertThat(result.exceptionOrNull()).isInstanceOf(RuntimeException::class.java)
            assertThat(result.exceptionOrNull()?.message).isEqualTo("Game not found")

            coVerify { mockBoardGameService.getGameDetailById(gameId) }
        }

    @Test
    fun `should handle null response gracefully`() =
        runTest {
            val gameId = "null_game"

            coEvery { mockBoardGameService.getGameDetailById(gameId) } returns null

            val result = gameRepository.getGameDetail(gameId)

            assertThat(result.isFailure).isTrue()
            assertThat(result.exceptionOrNull()).isInstanceOf(IllegalStateException::class.java)
        }

    @Test
    fun `should transform response correctly to domain model`() =
        runTest {
            val gameId = "transform_test"
            val mockResponse = GameDetailResponse(
                id = gameId,
                name = "Transform Test Game",
                description = "Testing transformation",
                yearPublished = 1996,
                minimumPlayers = 1,
                maximumPlayers = 6,
                minimumPlayingTime = 15,
                maximumPlayingTime = 120,
                minimumPlayerAge = 8,
                suggestedPlayersAgePoll = listOf(
                    SuggestedPlayersAgePoll("10", 5),
                    SuggestedPlayersAgePoll("12", 3),
                ),
                categories = listOf(
                    CategoryResponse("cat1", "Family"),
                    CategoryResponse("cat2", "Strategy"),
                ),
                mechanic = listOf(
                    MechanicResponse("mech1", "Dice Rolling"),
                    MechanicResponse("mech2", "Hand Management"),
                ),
                expansions = emptyList(),
                publishers = listOf(
                    PublisherResponse("pub1", "Amazing Games"),
                ),
                developmentTeam = listOf(
                    DevelopmentTeamMemberResponse("dev1", "John Doe", "Designer"),
                    DevelopmentTeamMemberResponse("dev2", "Jane Smith", "Artist"),
                ),
                statistics = GameStatisticsResponse(
                    usersOwned = 500,
                    averageRating = "7.85",
                    totalVotes = 124,
                    shoppingStatistics = ShoppingStatisticsResponse(10, 15, 25),
                ),
            )

            coEvery { mockBoardGameService.getGameDetailById(gameId) } returns mockResponse

            val result = gameRepository.getGameDetail(gameId)

            assertThat(result.isSuccess).isTrue()
            val gameDetail = result.getOrNull()!!

            assertThat(gameDetail.categories).hasSize(2)
            assertThat(gameDetail.mechanics).hasSize(2)
            assertThat(gameDetail.publishers).hasSize(1)
            assertThat(gameDetail.developmentTeam).hasSize(2)
            assertThat(gameDetail.suggestedPlayersAgePoll).hasSize(2)
            assertThat(gameDetail.statistics?.usersOwned).isEqualTo(500)
            assertThat(gameDetail.statistics?.averageRating).isEqualTo(7.85)
        }

    @Test
    fun `should handle partial data response`() =
        runTest {
            val gameId = "partial_data"
            val mockResponse = GameDetailResponse(
                id = gameId,
                name = "Partial Game",
                description = "Game with missing data",
                yearPublished = 2023,
                minimumPlayers = 2,
                maximumPlayers = 4,
                minimumPlayingTime = null,
                maximumPlayingTime = null,
                minimumPlayerAge = null,
                suggestedPlayersAgePoll = emptyList(),
                categories = null,
                mechanic = null,
                expansions = null,
                publishers = null,
                developmentTeam = null,
                statistics = null,
            )

            coEvery { mockBoardGameService.getGameDetailById(gameId) } returns mockResponse

            val result = gameRepository.getGameDetail(gameId)

            assertThat(result.isSuccess).isTrue()
            val gameDetail = result.getOrNull()!!

            assertThat(gameDetail.categories).isEmpty()
            assertThat(gameDetail.mechanics).isEmpty()
            assertThat(gameDetail.publishers).isEmpty()
            assertThat(gameDetail.statistics).isNull()
            assertThat(gameDetail.hasRating).isFalse()
        }
}
