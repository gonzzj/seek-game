package com.ort.seekgame.entities

data class Game(
    var id: Int,
    var name: String,
    var background_image: String,
    var description: String,
    var metacritic: Int,
)

data class GamesResponse(
    var count: Int,
    var next: String,
    var previous: String,
    var results: List<Game>
)

data class PlatformGame(
    var platform: Platform,
    var released_at: String
)