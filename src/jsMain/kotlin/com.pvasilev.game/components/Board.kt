package com.pvasilev.game.components

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

class Board(props: Props) : RComponent<Board.Props, Board.State>(props) {

    override fun State.init(props: Props) {
        values = Array(props.size) {
            Array<Int?>(props.size) {
                null
            }
        }
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