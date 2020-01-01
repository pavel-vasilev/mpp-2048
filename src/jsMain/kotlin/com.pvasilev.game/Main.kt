package com.pvasilev.game

import com.pvasilev.game.components.board
import react.dom.render
import kotlin.browser.document

fun main() {
    render(document.getElementById("root")) {
        board {
            size = 4
        }
    }
}