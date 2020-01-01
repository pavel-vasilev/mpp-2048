package com.pvasilev.game.components

import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener
import org.w3c.dom.events.KeyboardEvent
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import kotlin.browser.document

class Board(props: Props) : RComponent<Board.Props, Board.State>(props), EventListener {

    private companion object {
        const val KEYCODE_UP = 38
        const val KEYCODE_DOWN = 40
        const val KEYCODE_LEFT = 37
        const val KEYCODE_RIGHT = 39
        const val KEYDOWN_EVENT = "keydown"
    }

    override fun State.init(props: Props) {
        values = Array(props.size) {
            Array<Int?>(props.size) {
                null
            }
        }
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
                                number = state.values[y][x]
                            }
                        }
                    }
                }
            }
        }
    }

    override fun handleEvent(event: Event) {
        if (event is KeyboardEvent) {
            when (event.keyCode) {
                KEYCODE_UP -> console.log("up")
                KEYCODE_DOWN -> console.log("down")
                KEYCODE_LEFT -> console.log("left")
                KEYCODE_RIGHT -> console.log("right")
            }
        }
    }

    interface Props : RProps {
        var size: Int
    }

    interface State : RState {
        var values: Array<Array<Int?>>
    }
}

fun RBuilder.board(handler: Board.Props.() -> Unit) {
    child(Board::class) {
        attrs(handler)
    }
}