@file:JsModule("react-transition-group")

package com.pvasilev.game.contrib.transitions

import react.RClass
import react.RProps

external val CSSTransition: RClass<CSSTransitionProps>

external interface CSSTransitionProps : RProps {
    var `in`: Boolean

    var appear: Boolean

    var timeout: Int

    var classNames: String
}