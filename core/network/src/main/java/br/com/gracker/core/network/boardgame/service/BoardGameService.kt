package br.com.gracker.core.network.boardgame.service

import br.com.gracker.core.network.boardgame.service.response.GameDetailResponse
import retrofit2.http.GET

interface BoardGameService {
    @GET
    fun getGameDetailById(gameId: String): GameDetailResponse
}
