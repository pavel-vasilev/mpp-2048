package com.pvasilev.game.actions

import com.pvasilev.game.models.State
import com.pvasilev.game.models.Status
import com.pvasilev.game.models.Tile
import com.pvasilev.game.reducers.Reducible
import redux.RAction
import kotlin.math.min

object Reset : RAction, Reducible {

    override fun reduce(state: State): State {
        return State.initialState
    }
}

class CreateTiles(private val amount: Int) : RAction, Reducible {

    override fun reduce(state: State): State {
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

        return state.copy(tiles = state.tiles + tiles)
    }
}

class ChangeStatus(private val status: Status) : RAction, Reducible {

    override fun reduce(state: State): State {
        return state.copy(status = status)
    }
}

object UpdateScore : RAction, Reducible {

    override fun reduce(state: State): State {
        val score = state.tiles
            .groupBy { it.x to it.y }
            .filter { (_, values) -> values.size > 1 }
            .mapValues { (_, values) -> values.sumBy(Tile::value) }
            .values
            .sum()

        return state.copy(score = state.score + score)
    }
}

object Merge : RAction, Reducible {

    override fun reduce(state: State): State {
        val tiles = state.tiles
            .groupBy { it.x to it.y }
            .mapValues { (_, values) ->
                values.reduce { acc, tile -> acc.copy(id = min(acc.id, tile.id), value = acc.value * 2) }
            }
            .values
            .sortedBy { it.id }

        return state.copy(tiles = tiles)
    }
}

class Move(private val direction: Direction) : RAction, Reducible {

    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }

    override fun reduce(state: State): State {
        val tiles = state.tiles
            .groupBy { if (direction in setOf(Direction.UP, Direction.DOWN)) it.x else it.y }
            .mapValues { (_, values) ->
                when (direction) {
                    Direction.UP -> values.sortedBy(Tile::y)
                    Direction.DOWN -> values.sortedByDescending(Tile::y)
                    Direction.LEFT -> values.sortedBy(Tile::x)
                    else -> values.sortedByDescending(Tile::x)
                }
            }
            .mapValues { (_, values) ->
                when (direction) {
                    Direction.UP -> values.mapIndexed { index, tile -> tile.copy(y = index) }
                    Direction.DOWN -> values.mapIndexed { index, tile -> tile.copy(y = state.size - 1 - index) }
                    Direction.LEFT -> values.mapIndexed { index, tile -> tile.copy(x = index) }
                    else -> values.mapIndexed { index, tile -> tile.copy(x = state.size - 1 - index) }
                }
            }
            .mapValues { (_, values) ->
                val index = values.windowed(size = 2, step = 1)
                    .indexOfFirst { window -> window[0].value == window[1].value }

                if (index != -1) {
                    values.subList(0, index + 1) + values.subList(index + 1, values.size)
                        .map {
                            when (direction) {
                                Direction.UP -> it.copy(y = it.y - 1)
                                Direction.DOWN -> it.copy(y = it.y + 1)
                                Direction.LEFT -> it.copy(x = it.x - 1)
                                else -> it.copy(x = it.x + 1)
                            }
                        }
                } else {
                    values
                }
            }
            .flatMap { it.value }
            .sortedBy { it.id }

        return state.copy(tiles = tiles)
    }
}