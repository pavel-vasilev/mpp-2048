package com.pvasilev.game.actions

import redux.RAction

class Move(val direction: Direction) : RAction {
    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }
}

object Reset : RAction