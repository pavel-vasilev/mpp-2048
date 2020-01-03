package com.pvasilev.game.styles

import com.pvasilev.game.models.Tile
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
        transition(duration = 300.ms)
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

    fun `for`(tile: Tile): RuleSet =
        when (tile.value) {
            2 -> tile2
            4 -> tile4
            8 -> tile8
            16 -> tile16
            32 -> tile32
            64 -> tile64
            128 -> tile128
            256 -> tile256
            else -> tile512
        }
}