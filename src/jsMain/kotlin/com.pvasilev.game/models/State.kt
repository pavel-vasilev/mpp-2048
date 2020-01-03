package com.pvasilev.game.models

data class State(
    val score: Int,
    val size: Int,
    val tiles: List<Tile>
)