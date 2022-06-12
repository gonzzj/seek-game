package com.example.seekgame.entities

data class Developer(
    var id: Number,
    var name: String,
    var image_background: String
)

data class DevelopersResponse(
    var count: Number,
    var next: String,
    var previous: String,
    var results: List<Developer>
)