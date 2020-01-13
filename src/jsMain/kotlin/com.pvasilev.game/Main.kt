package com.pvasilev.game

import com.pvasilev.game.actions.AddNew
import com.pvasilev.game.components.board
import com.pvasilev.game.middleware.rulesMiddleware
import com.pvasilev.game.models.State
import com.pvasilev.game.reducers.reducer
import react.dom.render
import react.redux.provider
import redux.applyMiddleware
import redux.compose
import redux.createStore
import redux.rEnhancer
import kotlin.browser.document

fun main() {
    val store = createStore(::reducer, State.initialState, compose(applyMiddleware(::rulesMiddleware), rEnhancer()))
    store.dispatch(AddNew(2))
    render(document.getElementById("root")) {
        provider(store) {
            board {}
        }
    }
}