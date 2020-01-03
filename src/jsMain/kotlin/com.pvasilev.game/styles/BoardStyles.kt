package com.pvasilev.game.styles

import kotlinx.css.*
import styled.StyleSheet

object BoardStyles : StyleSheet("BoardStyles") {
    val container by css {
        position = Position.absolute
        background = "#bbad9F"
        borderRadius = 6.px
        padding(8.px)
    }

    val row by css {
        height = 100.px
        marginBottom = 8.px
        lastChild {
            marginBottom = 0.px
        }
    }

    val cell by css {
        display = Display.inlineBlock
        width = 100.px
        height = 100.px
        background = "#cdc1b3"
        borderRadius = 3.px
        marginRight = 8.px
        lastChild {
            marginRight = 0.px
        }
    }
}