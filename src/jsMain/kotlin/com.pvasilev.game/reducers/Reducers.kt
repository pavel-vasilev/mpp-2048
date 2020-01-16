package com.pvasilev.game.reducers

import com.pvasilev.game.models.State
import redux.RAction

fun reducer(state: State, action: RAction): State {
    return when (action) {
        is Reducible -> action.reduce(state)
        else -> state
    }
}