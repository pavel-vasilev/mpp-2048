package com.pvasilev.game.components

import com.pvasilev.game.actions.Move
import com.pvasilev.game.models.State
import com.pvasilev.game.models.Tile
import com.pvasilev.game.styles.BoardStyles
import com.pvasilev.game.styles.TileStyles
import kotlinx.css.left
import kotlinx.css.px
import kotlinx.css.top
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener
import org.w3c.dom.events.KeyboardEvent
import react.*
import react.redux.rConnect
import redux.WrapperAction
import styled.css
import styled.styledDiv
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
        styledDiv {
            css {
                +BoardStyles.container
            }
            props.tiles.forEach { tile ->
                styledDiv {
                    css {
                        +TileStyles.tile
                        +TileStyles.`for`(tile)
                        left = (tile.x * 100 + 8 * (tile.x + 1)).px
                        top = (tile.y * 100 + 8 * (tile.y + 1)).px
                    }
                    +tile.value.toString()
                }
            }
            for (y in 0 until props.size) {
                styledDiv {
                    css {
                        +BoardStyles.row
                    }
                    for (x in 0 until props.size) {
                        styledDiv {
                            css {
                                +BoardStyles.cell
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
        var tiles: List<Tile>
        var onMove: (Move.Direction) -> Unit
    }
}

private interface BoardStateProps : RProps {
    var size: Int
    var tiles: List<Tile>
}

private interface BoardDispatchProps : RProps {
    var onMove: (Move.Direction) -> Unit
}

val board = rConnect<State, Move, WrapperAction, RProps, BoardStateProps, BoardDispatchProps, Board.Props>(
    { state, _ ->
        size = state.size
        tiles = state.tiles
    },
    { dispatch, _ ->
        onMove = { dispatch(Move(it)) }
    }
)(Board::class.js.unsafeCast<RClass<Board.Props>>())