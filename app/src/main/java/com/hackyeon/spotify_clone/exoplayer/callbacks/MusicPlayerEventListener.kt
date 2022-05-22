package com.hackyeon.spotify_clone.exoplayer.callbacks

import android.widget.Toast
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.hackyeon.spotify_clone.exoplayer.MusicService

class MusicPlayerEventListener(
    private val musicService: MusicService
): Player.Listener {

    // todo onPlayStateChanged 대신 사용하는거라 사용법을 다시 확인해봐야함
    // 밑에 onPlayWhenReadChanged 사용하는지 확인 필요
    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        if(playbackState == Player.STATE_READY) {
            musicService.stopForeground(false)
        }
    }
//    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
//        super.onPlayWhenReadyChanged(playWhenReady, reason)
//    }
    // Deprecated 되었음 위에 두개중 어떤거 써야할지 확인해야함
//    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
//        super.onPlayerStateChanged(playWhenReady, playbackState)
//        if(playbackState == Player.STATE_READY && !playWhenReady) {
//            musicService.stopForeground(false)
//        }
//    }



    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        Toast.makeText(musicService, "## ${this.javaClass.simpleName} ## onPlayerError", Toast.LENGTH_SHORT).show()
    }
}