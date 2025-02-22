package com.team23.neuracrsrecipes.model.action

sealed interface HomeAction {

    data object RefreshRecipes: HomeAction
}
