package com.example.cardapicrudpractice.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Real implementation of the [CardApi] using Retrofit.
 *
 * This object provides a Retrofit instance configured with a fake base URL.
 * Replace the [BASE_URL] with your actual API endpoint when available.
 */
object RetrofitApi {

    /**
     * The base URL for the API.
     */
    private const val BASE_URL = "https://fakeapi.example.com/"

    /**
     * Retrofit instance configured with the base URL and Gson converter.
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * The real API instance implementing [CardApi].
     */
    val api: CardApi by lazy {
        retrofit.create(CardApi::class.java)
    }
}