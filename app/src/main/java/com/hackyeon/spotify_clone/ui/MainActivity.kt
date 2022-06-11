package com.hackyeon.spotify_clone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.RequestManager
import com.hackyeon.spotify_clone.R
import com.hackyeon.spotify_clone.adapters.SwipeSongAdapter
import com.hackyeon.spotify_clone.data.entities.Song
import com.hackyeon.spotify_clone.databinding.ActivityMainBinding
import com.hackyeon.spotify_clone.exoplayer.toSong
import com.hackyeon.spotify_clone.other.Status
import com.hackyeon.spotify_clone.ui.viewmodels.MainViewModel
import com.hackyeon.spotify_clone.ui.viewmodels.MainViewModel_Factory
import com.hackyeon.spotify_clone.ui.viewmodels.MainViewModel_HiltModules
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var glide: RequestManager
    @Inject
    lateinit var swipeSongAdapter: SwipeSongAdapter

    private var curPlayingSong: Song? = null

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bindUI()
        subscribeToObservers()
    }

    private fun bindUI() {
        binding.vpSong.adapter = swipeSongAdapter
    }

    private fun switchViewPagerToCurrentSong(song: Song) {
        val newItemIndex =  swipeSongAdapter.songs.indexOf(song)
        if(newItemIndex != -1) {
            binding.vpSong.currentItem = newItemIndex
            curPlayingSong = song
        }
    }

    private fun subscribeToObservers() {
        mainViewModel.mediaItems.observe(this) {
            it?.let { result ->
                when(result.status) {
                    Status.SUCCESS -> {
                        result.data?.let { songs ->
                            swipeSongAdapter.songs = songs
                            if(songs.isNotEmpty()) {
                                glide
                                    .load((curPlayingSong?: songs[0]).imageUrl)
                                    .into(binding.ivCurSongImage)
                            }
                            switchViewPagerToCurrentSong(curPlayingSong?: return@observe)
                        }
                    }
                    Status.LOADING -> Unit
                    Status.ERROR -> Unit
                }
            }
        }
        mainViewModel.curPlayingSong.observe(this) {
            it?.let {
                curPlayingSong = it.toSong()
                glide
                    .load(curPlayingSong?.imageUrl)
                    .into(binding.ivCurSongImage)
                switchViewPagerToCurrentSong(curPlayingSong?: return@observe)
            }
        }

    }

}