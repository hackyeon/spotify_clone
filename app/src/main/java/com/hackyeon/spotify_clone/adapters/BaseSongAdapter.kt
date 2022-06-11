package com.hackyeon.spotify_clone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.hackyeon.spotify_clone.R
import com.hackyeon.spotify_clone.adapters.viewholders.SongViewHolder
import com.hackyeon.spotify_clone.adapters.viewholders.SwipeSongViewHolder
import com.hackyeon.spotify_clone.data.entities.Song
import com.hackyeon.spotify_clone.databinding.ListItemBinding
import com.hackyeon.spotify_clone.databinding.SwipeItemBinding

abstract class BaseSongAdapter(
    private val layoutId: Int
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected val diffCallback = object: DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    protected abstract val differ: AsyncListDiffer<Song>

    var songs: List<Song>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(layoutId) {
            R.layout.list_item -> {
                val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SongViewHolder(binding)
            }
            R.layout.swipe_item -> {
                val binding = SwipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SwipeSongViewHolder(binding)
            }
            else -> {
                val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SongViewHolder(binding)
            }
        }
    }


    protected var onItemClickListener: ((Song) -> Unit)? = null

    fun setItemClickListener(listener: (Song) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = songs.size
}