package com.example.seekgame.entities

data class Game(
    var id: Number,
    var name: String,
    var background_image: String
)

data class GamesResponse(
    var count: Number,
    var next: String,
    var previous: String,
    var results: List<Game>
)