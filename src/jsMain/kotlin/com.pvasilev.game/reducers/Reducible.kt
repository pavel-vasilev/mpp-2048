package com.pvasilev.game.reducers

import com.pvasilev.game.models.State

interface Reducible {
    fun reduce(state: State): State
}