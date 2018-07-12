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

package com.edoubletech.moviemania.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edoubletech.moviemania.R
import com.edoubletech.moviemania.data.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

/**
 * This is adapter is used by a recycler view to bind data from the API to the appropriate view
 */
class MainMovieAdapter : ListAdapter<Movie, MainMovieAdapter.MovieViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = getItem(position)
        holder.bindData(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                    oldItem == newItem
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieImage = itemView.movieImageView
        private val movieName = itemView.movieNameTextView
        private val movieRating = itemView.ratingTextView


        fun bindData(movie: Movie) {
            val resources = itemView.resources

            val imageUrl: String?
            if (movie.backdrop_path != null) {

                imageUrl = resources.getString(R.string.image_base_url) + resources.getString(R.string.image_size_300) + movie.backdrop_path
                Glide.with(itemView.context).load(imageUrl).into(movieImage)
            } else {
                movieImage.visibility = View.GONE
            }

            movieName.text = movie.title

            movieRating.text = movie.vote_average.toString()
        }
    }
}