package com.pvasilev.game.models

data class State(
    val score: Int,
    val size: Int,
    val tiles: List<Tile>
) {
    companion object {
        val initialState = State(score = 0, size = 4, tiles = emptyList())
    }
}