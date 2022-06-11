package com.hackyeon.spotify_clone.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.hackyeon.spotify_clone.data.entities.Song
import com.hackyeon.spotify_clone.databinding.SwipeItemBinding

class SwipeSongViewHolder(private val binding: SwipeItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(song: Song, callback: () -> Unit) {
        binding.tvPrimary.text = "${song.title} - ${song.subtitle}"
        binding.root.setOnClickListener {
            callback()
        }
    }
}