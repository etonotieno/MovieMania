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

package com.edoubletech.moviemania.data.model

/**
 * This model represents a Movie object that we get back from the API.
 * It also represents a Room entity that will be used to represent a table in the Room database.
 */
data class Movie(
        val poster_path: String?,
        val adult: Boolean,
        val overview: String,
        val release_date: String,
        val genre_ids: ArrayList<Int>,
        val id: Int,
        val original_title: String,
        val original_language: String,
        val title: String,
        val backdrop_path: String?,
        val popularity: Double,
        val vote_count: Int,
        val video: Boolean,
        val vote_average: Double
)