package br.com.gracker.core.network.boardgame.repository

import br.com.gracker.core.network.boardgame.domain.GameDetail
import br.com.gracker.core.network.boardgame.mapper.toDomain
import br.com.gracker.core.network.boardgame.service.BoardGameService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GameRepositoryImpl
    @Inject
    constructor(
        private val boardGameService: BoardGameService,
    ) : GameRepository {
        override suspend fun getGameDetail(gameId: String): Result<GameDetail> {
            return try {
                val response = boardGameService.getGameDetailById(gameId)
                    ?: return Result.failure(IllegalStateException("Game not found"))

                val gameDetail = response.toDomain()
                Result.success(gameDetail)
            } catch (e: HttpException) {
                Result.failure(e)
            } catch (e: IOException) {
                Result.failure(e)
            } catch (e: IllegalStateException) {
                Result.failure(e)
            }
        }
    }
