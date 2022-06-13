package com.example.seekgame.entities

data class Platform(
    var id: Int,
    var name: String,
    var image_background: String
)

data class PlatformsResponse(
    var count: Int,
    var next: String,
    var previous: String,
    var results: List<Platform>
)