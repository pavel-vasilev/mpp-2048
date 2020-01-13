package com.pvasilev.game.reducers

import com.pvasilev.game.actions.*
import com.pvasilev.game.models.State
import com.pvasilev.game.models.Tile
import redux.RAction

fun reducer(state: State, action: RAction): State {
    return when (action) {
        is Reset -> State.initialState
        is ChangeStatus -> state.copy(status = action.status)
        is Move -> state.copy(tiles = move(state, action.direction))
        is AddNew -> state.copy(tiles = state.tiles + generate(state, action.amount))
        else -> state
    }
}

fun generate(state: State, amount: Int): List<Tile> {
    val tiles = mutableListOf<Tile>()

    repeat(amount) {
        val coordinates = mutableListOf<Pair<Int, Int>>()

        for (y in (0 until state.size)) {
            for (x in (0 until state.size)) {
                if (state.tiles.none { it.x == x && it.y == y }) {
                    coordinates += x to y
                }
            }
        }

        val (x, y) = coordinates.random()
        val tile = Tile.at(x, y)
        tiles += tile
    }

    return tiles
}

fun move(state: State, direction: Move.Direction): List<Tile> =
    state.tiles
        .groupBy { if (direction in setOf(Move.Direction.UP, Move.Direction.DOWN)) it.x else it.y }
        .mapValues { (_, tiles) ->
            when (direction) {
                Move.Direction.UP -> tiles.sortedBy { it.y }
                Move.Direction.DOWN -> tiles.sortedByDescending { it.y }
                Move.Direction.LEFT -> tiles.sortedBy { it.x }
                else -> tiles.sortedByDescending { it.x }
            }
        }
        .mapValues { (_, tiles) ->
            when (direction) {
                Move.Direction.UP -> tiles.mapIndexed { index, tile -> tile.copy(y = index) }
                Move.Direction.DOWN -> tiles.mapIndexed { index, tile -> tile.copy(y = state.size - 1 - index) }
                Move.Direction.LEFT -> tiles.mapIndexed { index, tile -> tile.copy(x = index) }
                else -> tiles.mapIndexed { index, tile -> tile.copy(x = state.size - 1 - index) }
            }
        }
        .flatMap { it.value }
        .sortedBy { it.id }