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

package com.edoubletech.moviemania.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edoubletech.moviemania.Injector
import com.edoubletech.moviemania.data.model.Movie
import com.edoubletech.moviemania.data.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * This is a repository class that enables us to abstract away the source of the data we get.
 * The data can be fetched from the Network or the Database and this class enables us to hide the
 * implementation details to the ViewModel
 */
class MovieRepository {

    private val service = Injector.provideMovieService()
    private val mutableListOfMovies = MutableLiveData<List<Movie>>()

    init {
        service.getPopularMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {

                Log.e("Movie Repository", "Error getting the movies", t)
            }

            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                val movieResponse = response?.body()
                val listOfPopularMovies = movieResponse?.results
                mutableListOfMovies.value = listOfPopularMovies
            }

        })
    }

    fun getPopularMovies(): LiveData<List<Movie>> {
        return mutableListOfMovies
    }
}