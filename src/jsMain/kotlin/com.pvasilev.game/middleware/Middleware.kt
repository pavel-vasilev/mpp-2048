package com.pvasilev.game.middleware

import com.pvasilev.game.actions.AddNew
import com.pvasilev.game.actions.ChangeStatus
import com.pvasilev.game.actions.Merge
import com.pvasilev.game.actions.Move
import com.pvasilev.game.models.State
import com.pvasilev.game.models.Status
import com.pvasilev.game.models.Tile
import redux.MiddlewareApi
import redux.RAction
import redux.WrapperAction
import kotlin.browser.window

fun rulesMiddleware(store: MiddlewareApi<State, RAction, WrapperAction>): ((RAction) -> WrapperAction) -> (RAction) -> WrapperAction {
    return { next ->
        { action ->
            try {
                val oldTiles = store.getState().tiles
                val result = next(action)
                val newTiles = store.getState().tiles

                if (action is Move && oldTiles != newTiles) {
                    window.setTimeout({
                        next(Merge)
                        next(AddNew(1))
                    }, 300)
                }

                if (newTiles.any { it.value == Tile.WIN_VALUE }) {
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