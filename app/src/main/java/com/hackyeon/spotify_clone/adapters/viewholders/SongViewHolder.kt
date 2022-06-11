package com.hackyeon.spotify_clone.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.hackyeon.spotify_clone.data.entities.Song
import com.hackyeon.spotify_clone.databinding.ListItemBinding

class SongViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(song: Song, glide: RequestManager, callback: () -> Unit) {
        binding.tvPrimary.text = song.title
        binding.tvSecondary.text = song.subtitle
        glide.load(song.imageUrl).into(binding.ivItemImage)

        binding.root.setOnClickListener {
            callback()
        }
    }
}