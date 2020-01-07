package com.pvasilev.game

import com.pvasilev.game.actions.AddNew
import com.pvasilev.game.components.board
import com.pvasilev.game.models.State
import com.pvasilev.game.reducers.reducer
import react.dom.render
import react.redux.provider
import redux.createStore
import redux.rEnhancer
import kotlin.browser.document

fun main() {
    val store = createStore(::reducer, State.initialState, rEnhancer())
    store.dispatch(AddNew(2))
    render(document.getElementById("root")) {
        provider(store) {
            board {}
        }
    }
}