package com.pvasilev.game.styles

import kotlinx.css.*
import styled.StyleSheet

object AlertStyles : StyleSheet("AlertStyles") {
    val container by css {
        width = 100.pct
        height = 100.pct
        top = 0.px
        left = 0.px
        position = Position.absolute
        display = Display.flex
        backgroundColor = Color("#eee4da80")
        flexDirection = FlexDirection.column
        justifyContent = JustifyContent.center
    }

    val message by css {
        fontSize = 60.px
        fontWeight = FontWeight.bold
        fontFamily = "Helvetica Neue"
        color = Color("#776e65")
        textAlign = TextAlign.center
    }

    val link by css {
        cursor = Cursor.pointer
        color = Color("#f9f6f2")
        borderRadius = 3.px
        backgroundColor = Color("#8f7a66")
        fontWeight = FontWeight.bold
        fontFamily = "Helvetica Neue"
        margin(horizontal = 10.px)
        padding(horizontal = 20.px, vertical = 10.px)
    }
}