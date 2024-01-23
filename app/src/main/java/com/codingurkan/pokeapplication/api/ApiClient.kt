package com.codingurkan.pokeapplication.api

import com.codingurkan.pokeapplication.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    @Singleton
    @Provides
    fun create() : ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client())
            .build()

        return retrofit.create(ApiService::class.java)
    }

    private fun client(): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            retryOnConnectionFailure(false)
            callTimeout(15L, TimeUnit.SECONDS)
            connectTimeout(15L, TimeUnit.SECONDS)
            readTimeout(15L, TimeUnit.SECONDS)
            writeTimeout(15,TimeUnit.SECONDS)
            this.build()
        }
    }
}