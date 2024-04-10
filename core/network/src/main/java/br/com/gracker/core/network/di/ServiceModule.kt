package br.com.gracker.core.network.di

import br.com.gracker.core.network.boardgame.service.BoardGameService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object ServiceModule {
    @Provides
    fun providesBoardGameService(retrofit: Retrofit) = retrofit.create(BoardGameService::class.java)
}
