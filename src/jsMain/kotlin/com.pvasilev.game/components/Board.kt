package com.pvasilev.game.components

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.table
import react.dom.tbody
import react.dom.td
import react.dom.tr

class Board(props: Props) : RComponent<Board.Props, Board.State>(props) {

    override fun State.init(props: Props) {
        values = Array(props.size) {
            IntArray(props.size)
        }
    }

    override fun RBuilder.render() {
        table {
            tbody {
                for (y in 0 until props.size) {
                    tr {
                        for (x in 0 until props.size) {
                            td {}
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
        var values: Array<IntArray>
    }
}

fun RBuilder.board(handler: Board.Props.() -> Unit) {
    child(Board::class) {
        attrs(handler)
    }
}