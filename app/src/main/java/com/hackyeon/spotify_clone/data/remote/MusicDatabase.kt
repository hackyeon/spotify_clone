package com.hackyeon.spotify_clone.data.remote

import com.hackyeon.spotify_clone.data.entities.Song

class MusicDatabase {

    suspend fun getAllSongs(): List<Song> {
        val list = mutableListOf<Song>()
        val first = Song("1", "first title", "first artist", "https://skek933.cafe24.com/music/Over_the_Horizon.mp3", "https://skek933.cafe24.com/music/thumb.PNG")
        list.add(first)
        return list
    }

}