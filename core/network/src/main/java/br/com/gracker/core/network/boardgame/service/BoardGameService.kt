package br.com.gracker.core.network.boardgame.service

import br.com.gracker.core.network.boardgame.service.response.GameDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BoardGameService {
    @GET("game/{gameId}")
    suspend fun getGameDetailById(
        @Path("gameId") gameId: String,
    ): GameDetailResponse?
}
