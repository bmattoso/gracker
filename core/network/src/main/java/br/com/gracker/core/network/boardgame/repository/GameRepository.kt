package br.com.gracker.core.network.boardgame.repository

import br.com.gracker.core.network.boardgame.domain.GameDetail

interface GameRepository {
    suspend fun getGameDetail(gameId: String): Result<GameDetail>
}
