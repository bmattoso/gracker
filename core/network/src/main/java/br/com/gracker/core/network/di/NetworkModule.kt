package br.com.gracker.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://boardgamegeek.com/xmlapi2/"
private const val TIMEOUT_SECONDS = 60L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideOkHttp() =
        OkHttpClient.Builder().apply {
            connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        }.build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(okHttpClient)
        }.build()
}
