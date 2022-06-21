package com.ort.seekgame.entities

data class User(
    var email: String?,
    var banner: String?,
    var icon: String?,
    var games: List<Game>
)