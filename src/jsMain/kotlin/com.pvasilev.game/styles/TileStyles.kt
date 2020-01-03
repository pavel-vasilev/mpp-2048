package com.pvasilev.game.styles

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import styled.StyleSheet

object TileStyles : StyleSheet("TileStyles") {
    val tile by css {
        position = Position.absolute
        width = 100.px
        height = 100.px
        borderRadius = 3.px
        lineHeight = LineHeight("100px")
        fontSize = 48.px
        fontWeight = FontWeight.bold
        fontFamily = "Helvetica Neue"
        textAlign = TextAlign.center
        transition(duration = 600.ms)
    }

    val tile2 by css {
        background = "#eee4da"
        color = Color("#776e65")
    }

    val tile4 by css {
        background = "#ede0c8"
        color = Color("#776e65")
    }

    val tile8 by css {
        background = "#f2b179"
        color = Color("#f9f6f2")
    }

    val tile16 by css {
        background = "#f59563"
        color = Color("#f9f6f2")
    }


    val tile32 by css {
        background = "#f67c5f"
        color = Color("#f9f6f2")
    }

    val tile64 by css {
        background = "#f65e3b"
        color = Color("#f9f6f2")
    }

    val tile128 by css {
        background = "#edcf72"
        color = Color("#f9f6f2")
    }

    val tile256 by css {
        background = "#edcc61"
        color = Color("#f9f6f2")
    }

    val tile512 by css {
        background = "#edc850"
        color = Color("#f9f6f2")
    }
}