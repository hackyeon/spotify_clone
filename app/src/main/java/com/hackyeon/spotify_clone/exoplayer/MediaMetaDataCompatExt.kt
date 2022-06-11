package com.hackyeon.spotify_clone.exoplayer

import android.support.v4.media.MediaMetadataCompat
import com.hackyeon.spotify_clone.data.entities.Song

fun MediaMetadataCompat.toSong(): Song? {
    return description?.let {
        Song(
            it.mediaId.toString(),
            it.title.toString(),
            it.subtitle.toString(),
            it.mediaUri.toString(),
            it.iconUri.toString()
        )
    }
}