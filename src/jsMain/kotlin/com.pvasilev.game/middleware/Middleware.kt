package com.pvasilev.game.middleware

import com.pvasilev.game.actions.ChangeStatus
import com.pvasilev.game.models.State
import com.pvasilev.game.models.Status
import com.pvasilev.game.models.Tile
import redux.MiddlewareApi
import redux.RAction
import redux.WrapperAction

fun rulesMiddleware(store: MiddlewareApi<State, RAction, WrapperAction>): ((RAction) -> WrapperAction) -> (RAction) -> WrapperAction {
    return { next ->
        { action ->
            try {
                val result = next(action)
                if (store.getState().tiles.any { it.value == Tile.WIN_VALUE }) {
                    next(ChangeStatus(Status.WIN))
                } else {
                    result
                }
            } catch (e: NoSuchElementException) {
                next(ChangeStatus(Status.LOSE))
            }
        }
    }
}