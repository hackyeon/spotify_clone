package com.hackyeon.spotify_clone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.RequestManager
import com.hackyeon.spotify_clone.R
import com.hackyeon.spotify_clone.databinding.ActivityMainBinding
import com.hackyeon.spotify_clone.ui.viewmodels.MainViewModel
import com.hackyeon.spotify_clone.ui.viewmodels.MainViewModel_Factory
import com.hackyeon.spotify_clone.ui.viewmodels.MainViewModel_HiltModules
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var glide: RequestManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}