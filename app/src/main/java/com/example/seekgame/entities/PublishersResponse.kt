package com.example.seekgame.entities

data class Publisher(
    var id: Number,
    var image_background: String
)

data class PublishersResponse(
    var count: Number,
    var next: String,
    var previous: String,
    var results: List<Publisher>
)