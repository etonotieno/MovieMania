/*
 *    Copyright (C) 2018 Eton Otieno Oboch
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.edoubletech.moviemania

import com.edoubletech.moviemania.data.api.AuthKeyInterceptor
import com.edoubletech.moviemania.data.api.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This is an object used to inject different components to different classes.
 * This will be replaced by Dagger in future commits.
 */
object Injector {
    private const val BASE_URL= "https://api.themoviedb.org/3/"

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(AuthKeyInterceptor())
                .addInterceptor(provideLoggingInterceptor())
                .build()
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient())
                .build()
    }

    fun provideMovieService(): MovieService {
        return provideRetrofit().create(MovieService::class.java)
    }
}