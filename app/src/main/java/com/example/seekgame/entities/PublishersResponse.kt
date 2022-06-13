package com.example.seekgame.entities

data class Publisher(
    var id: Int,
    var name: String,
    var image_background: String
)

data class PublishersResponse(
    var count: Int,
    var next: String,
    var previous: String,
    var results: List<Publisher>
)