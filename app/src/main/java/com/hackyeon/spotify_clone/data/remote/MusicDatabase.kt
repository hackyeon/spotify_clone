package com.hackyeon.spotify_clone.data.remote

import com.hackyeon.spotify_clone.data.entities.Song

class MusicDatabase {

    suspend fun getAllSongs(): List<Song> {
        val list = mutableListOf<Song>()
        list.add(Song("1", "일번 타이틀", "일번 서브타이틀", "https://skek933.cafe24.com/music/1.mp3", "https://skek933.cafe24.com/music/1.PNG"))
        list.add(Song("2", "이번 타이틀", "이번 서브타이틀", "https://skek933.cafe24.com/music/2.mp3", "https://skek933.cafe24.com/music/2.PNG"))
        list.add(Song("3", "삼번 타이틀", "삼번 서브타이틀", "https://skek933.cafe24.com/music/3.mp3", "https://skek933.cafe24.com/music/3.PNG"))
        list.add(Song("4", "사번 타이틀", "사번 서브타이틀", "https://skek933.cafe24.com/music/4.mp3", "https://skek933.cafe24.com/music/4.PNG"))
        list.add(Song("5", "오번 타이틀", "오번 서브타이틀", "https://skek933.cafe24.com/music/5.mp3", "https://skek933.cafe24.com/music/5.PNG"))
        list.add(Song("6", "육번 타이틀", "육번 서브타이틀", "https://skek933.cafe24.com/music/6.mp3", "https://skek933.cafe24.com/music/6.PNG"))
        list.add(Song("7", "칠번 타이틀", "칠번 서브타이틀", "https://skek933.cafe24.com/music/7.mp3", "https://skek933.cafe24.com/music/7.PNG"))
        list.add(Song("8", "팔번 타이틀", "팔번 서브타이틀", "https://skek933.cafe24.com/music/8.mp3", "https://skek933.cafe24.com/music/8.PNG"))
        list.add(Song("9", "구번 타이틀", "구번 서브타이틀", "https://skek933.cafe24.com/music/9.mp3", "https://skek933.cafe24.com/music/9.PNG"))
        list.add(Song("10", "십번 타이틀", "십번 서브타이틀", "https://skek933.cafe24.com/music/10.mp3", "https://skek933.cafe24.com/music/10.PNG"))
        list.add(Song("11", "십일번 타이틀", "십일번 서브타이틀", "https://skek933.cafe24.com/music/11.mp3", "https://skek933.cafe24.com/music/11.PNG"))
        list.add(Song("12", "십이번 타이틀", "십이번 서브타이틀", "https://skek933.cafe24.com/music/12.mp3", "https://skek933.cafe24.com/music/12.PNG"))
        list.add(Song("13", "십삼번 타이틀", "십상번 서브타이틀", "https://skek933.cafe24.com/music/13.mp3", "https://skek933.cafe24.com/music/13.PNG"))
        list.add(Song("14", "십사번 타이틀", "십사번 서브타이틀", "https://skek933.cafe24.com/music/14.mp3", "https://skek933.cafe24.com/music/14.PNG"))
        list.add(Song("15", "십오번 타이틀", "십오번 서브타이틀", "https://skek933.cafe24.com/music/15.mp3", "https://skek933.cafe24.com/music/15.PNG"))
        list.add(Song("16", "십육번 타이틀", "십육번 서브타이틀", "https://skek933.cafe24.com/music/16.mp3", "https://skek933.cafe24.com/music/16.PNG"))
        list.add(Song("17", "십칠번 타이틀", "십칠번 서브타이틀", "https://skek933.cafe24.com/music/17.mp3", "https://skek933.cafe24.com/music/17.PNG"))
        return list
    }

}