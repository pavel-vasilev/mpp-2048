package com.pvasilev.game.models

data class Tile private constructor(
    val id: Int,
    val value: Int,
    val x: Int,
    val y: Int
) {
    companion object {
        const val WIN_VALUE = 2048

        private var id: Int = 0

        fun at(x: Int, y: Int, value: Int = 2) = Tile(++id, value, x, y)
    }
}