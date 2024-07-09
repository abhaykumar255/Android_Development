package com.example.musicappui.component

import androidx.annotation.DrawableRes
import com.example.musicappui.R

data class DummyLibrary(@DrawableRes val icon: Int, val name: String)

val dummyList = listOf<DummyLibrary>(
    DummyLibrary(R.drawable.baseline_playlist_add_24, "Playlist"),
    DummyLibrary(R.drawable.baseline_headset_mic_24, "Artist"),
    DummyLibrary(R.drawable.baseline_album_24, "Album"),
    DummyLibrary(R.drawable.baseline_music_note_24, "Song"),
    DummyLibrary(R.drawable.baseline_display_settings_24, "Genre")
)
