package com.team23.neuracrsrecipes.handler

import com.team23.neuracrsrecipes.model.action.UiAction

expect class UiActionHandler {
    fun handle(action: UiAction)
}
