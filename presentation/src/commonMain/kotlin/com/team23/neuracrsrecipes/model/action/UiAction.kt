package com.team23.neuracrsrecipes.model.action

sealed interface UiAction {

    data object RedirectToWebsite: UiAction
    data object LaunchSettings: UiAction
}
