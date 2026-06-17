package com.team23.neuracrsrecipes.mapper

import com.team23.domain.recipe.model.PromotedLaneDomainModel
import com.team23.neuracrsrecipes.model.uimodel.PromotedLaneUiModel
import popotev2.presentation.generated.resources.Res

class PromotedLaneUiMapper(
    private val summarizedRecipeUiMapper: SummarizedRecipeUiMapper,
) {

    fun toPromotedLaneUiModels(lanes: List<PromotedLaneDomainModel>): List<PromotedLaneUiModel> =
        lanes.map(::toPromotedLaneUiModel)

    fun toPromotedLaneUiModel(lane: PromotedLaneDomainModel): PromotedLaneUiModel =
        PromotedLaneUiModel(
            title = toTitle(lane.type),
            recipes = lane.recipes.map(summarizedRecipeUiMapper::toUiModel),
        )

    private fun toTitle(type: PromotedLaneDomainModel.Type): String = when(type) {
        PromotedLaneDomainModel.Type.Seasonal -> "Seasonal recipes"
        PromotedLaneDomainModel.Type.Vegetarian -> "Vegetarian recipes"
        PromotedLaneDomainModel.Type.Vegan -> "Vegan recipes"
    }
}
