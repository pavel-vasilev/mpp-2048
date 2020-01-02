package com.pvasilev.game.components

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

class Tile(props: Props) : RComponent<Tile.Props, RState>(props) {

    override fun RBuilder.render() {
        div(classes = "tile tile-${props.number ?: 0}") {
            div(classes = "tile-inner") {
                +(props.number?.toString() ?: "")
            }
        }
    }

    interface Props : RProps {
        var number: Int?
    }
}

fun RBuilder.tile(handler: Tile.Props.() -> Unit) {
    child(Tile::class) {
        attrs(handler)
    }
}