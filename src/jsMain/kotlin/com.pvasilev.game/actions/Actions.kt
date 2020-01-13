package com.pvasilev.game.actions

import com.pvasilev.game.models.Status
import redux.RAction

class Move(val direction: Direction) : RAction {
    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }
}

object Merge : RAction

class AddNew(val amount: Int) : RAction

class ChangeStatus(val status: Status) : RAction

object Reset : RAction