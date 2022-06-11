package com.hackyeon.spotify_clone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.hackyeon.spotify_clone.R
import com.hackyeon.spotify_clone.adapters.viewholders.SwipeSongViewHolder
import com.hackyeon.spotify_clone.data.entities.Song
import com.hackyeon.spotify_clone.databinding.ListItemBinding
import javax.inject.Inject

class SwipeSongAdapter: BaseSongAdapter(R.layout.swipe_item) {

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val song = songs[position]
        if(holder is SwipeSongViewHolder) {
            holder.bind(song) {
                onItemClickListener?.let { click ->
                    click(song)
                }
            }
        }
    }
}