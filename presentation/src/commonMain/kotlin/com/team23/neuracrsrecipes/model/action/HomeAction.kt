package com.team23.neuracrsrecipes.model.action

import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel

sealed interface HomeAction {

    data object RefreshRecipes: HomeAction
    data class ToggleFavorite(val recipe: SummarizedRecipeUiModel): HomeAction
    data object ShowLocalPhoneMessage: HomeAction
}
