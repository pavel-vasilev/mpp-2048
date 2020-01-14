package com.pvasilev.game.components

import com.pvasilev.game.styles.AlertStyles
import kotlinx.css.*
import react.*
import styled.css
import styled.styledA
import styled.styledDiv
import styled.styledP

class Alert(props: Props) : RComponent<Alert.Props, RState>(props) {
    override fun RBuilder.render() {
        styledDiv {
            css {
                + AlertStyles.container
            }
            styledP {
                css {
                    + AlertStyles.message
                }
                +props.message
            }
            styledDiv {
                css {
                    textAlign = TextAlign.center
                }
                styledA {
                    css {
                        + AlertStyles.link
                    }
                    +"Try Again"
                }
                styledA {
                    css {
                        + AlertStyles.link
                    }
                    +"Tweet"
                }
            }
        }
    }

    interface Props : RProps {
        var message: String
    }
}

fun RBuilder.alert(handler: Alert.Props.() -> Unit) {
    child(Alert::class) {
        attrs(handler)
    }
}