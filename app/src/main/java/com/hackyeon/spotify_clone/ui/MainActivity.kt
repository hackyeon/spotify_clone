package com.hackyeon.spotify_clone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.session.PlaybackStateCompat
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.hackyeon.spotify_clone.R
import com.hackyeon.spotify_clone.adapters.SwipeSongAdapter
import com.hackyeon.spotify_clone.data.entities.Song
import com.hackyeon.spotify_clone.databinding.ActivityMainBinding
import com.hackyeon.spotify_clone.exoplayer.isPlaying
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

    private var playbackState: PlaybackStateCompat? = null

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
        binding.vpSong.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(playbackState?.isPlaying == true) {
                    mainViewModel.playOrToggleSong(swipeSongAdapter.songs[position])
                } else {
                    curPlayingSong = swipeSongAdapter.songs[position]
                }

            }
        })

        binding.ivPlayPause.setOnClickListener {
            curPlayingSong?.let {
                mainViewModel.playOrToggleSong(it, true)
            }
        }

        swipeSongAdapter.setItemClickListener {
            findNavController(R.id.navHostFragment).navigate(R.id.globalActionToSongFragment)
        }

        findNavController(R.id.navHostFragment).addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.songFragment -> hideBottomBar()
                R.id.homeFragment -> showBottomBar()
                else -> showBottomBar()
            }
        }
    }

    private fun hideBottomBar() {
        binding.ivCurSongImage.visibility = View.GONE
        binding.vpSong.visibility = View.GONE
        binding.ivPlayPause.visibility = View.GONE
    }
    private fun showBottomBar() {
        binding.ivCurSongImage.visibility = View.VISIBLE
        binding.vpSong.visibility = View.VISIBLE
        binding.ivPlayPause.visibility = View.VISIBLE
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
        mainViewModel.playbackState.observe(this) {
            playbackState = it
            binding.ivPlayPause.setImageResource(
                if(playbackState?.isPlaying == true) R.drawable.ic_pause else R.drawable.ic_play
            )
        }
        mainViewModel.isConnected.observe(this) {
            it?.getContentIfNotHandled()?.let { result ->
                when(result.status) {
                    Status.ERROR -> {
                        Snackbar.make(binding.root, result.message?: "오류 발생", Snackbar.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
        mainViewModel.networkError.observe(this) {
            it?.getContentIfNotHandled()?.let { result ->
                when(result.status) {
                    Status.ERROR -> {
                        Snackbar.make(binding.root, result.message?: "오류 발생", Snackbar.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

    }

}