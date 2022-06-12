package com.example.seekgame.entities

data class Platform(
    var id: Number,
    var image_background: String,
    var image: String
)

data class PlatformsResponse(
    var count: Number,
    var next: String,
    var previous: String,
    var results: List<Platform>
)