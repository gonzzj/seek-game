package com.ort.seekgame.entities

data class Developer(
    var id: Int,
    var name: String,
    var image_background: String
)

data class DevelopersResponse(
    var count: Int,
    var next: String,
    var previous: String,
    var results: List<Developer>
)