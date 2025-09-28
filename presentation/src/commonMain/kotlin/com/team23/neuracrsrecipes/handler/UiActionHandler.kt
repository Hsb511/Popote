package com.team23.neuracrsrecipes.handler

import com.team23.neuracrsrecipes.model.action.UiAction

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class UiActionHandler {
    fun handle(action: UiAction)
}
