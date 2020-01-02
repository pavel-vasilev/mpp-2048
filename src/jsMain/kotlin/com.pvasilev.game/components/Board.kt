package com.pvasilev.game.components

import com.pvasilev.game.State
import com.pvasilev.game.actions.Move
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener
import org.w3c.dom.events.KeyboardEvent
import react.*
import react.dom.div
import react.redux.rConnect
import redux.WrapperAction
import kotlin.browser.document

class Board(props: Props) : RComponent<Board.Props, RState>(props), EventListener {

    private companion object {
        const val KEYCODE_UP = 38
        const val KEYCODE_DOWN = 40
        const val KEYCODE_LEFT = 37
        const val KEYCODE_RIGHT = 39
        const val KEYDOWN_EVENT = "keydown"
    }

    override fun componentWillMount() {
        document.addEventListener(KEYDOWN_EVENT, this)
    }

    override fun componentWillUnmount() {
        document.removeEventListener(KEYDOWN_EVENT, this)
    }

    override fun RBuilder.render() {
        div(classes = "grid-container") {
            for (y in 0 until props.size) {
                div(classes = "grid-row") {
                    for (x in 0 until props.size) {
                        div(classes = "grid-cell") {
                            tile {
                                number = props.values[y][x]
                            }
                        }
                    }
                }
            }
        }
    }

    override fun handleEvent(event: Event) {
        event.preventDefault()
        if (event is KeyboardEvent) {
            when (event.keyCode) {
                KEYCODE_UP -> props.onMove(Move.Direction.UP)
                KEYCODE_DOWN -> props.onMove(Move.Direction.DOWN)
                KEYCODE_LEFT -> props.onMove(Move.Direction.LEFT)
                KEYCODE_RIGHT -> props.onMove(Move.Direction.RIGHT)
            }
        }
    }

    interface Props : RProps {
        var size: Int
        var values: Array<Array<Int?>>
        var onMove: (Move.Direction) -> Unit
    }
}

private interface BoardStateProps : RProps {
    var size: Int
    var values: Array<Array<Int?>>
}

private interface BoardDispatchProps : RProps {
    var onMove: (Move.Direction) -> Unit
}

val board = rConnect<State, Move, WrapperAction, RProps, BoardStateProps, BoardDispatchProps, Board.Props>(
    { state, _ ->
        size = state.size
        values = state.values
    },
    { dispatch, _ ->
        onMove = { dispatch(Move(it)) }
    }
)(Board::class.js.unsafeCast<RClass<Board.Props>>())