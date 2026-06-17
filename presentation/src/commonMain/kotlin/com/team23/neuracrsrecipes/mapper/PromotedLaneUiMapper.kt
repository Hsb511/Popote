package com.team23.neuracrsrecipes.mapper

import com.team23.domain.recipe.model.PromotedLaneDomainModel
import com.team23.neuracrsrecipes.model.uimodel.PromotedLaneUiModel

class PromotedLaneUiMapper(
    private val summarizedRecipeUiMapper: SummarizedRecipeUiMapper,
) {

    fun toPromotedLaneUiModels(lanes: List<PromotedLaneDomainModel>): List<PromotedLaneUiModel> =
        lanes.map(::toPromotedLaneUiModel)

    fun toPromotedLaneUiModel(lane: PromotedLaneDomainModel): PromotedLaneUiModel =
        PromotedLaneUiModel(
            type = toUiType(lane.type),
            recipes = lane.recipes.map(summarizedRecipeUiMapper::toUiModel),
        )

    private fun toUiType(type: PromotedLaneDomainModel.Type): PromotedLaneUiModel.Type = when(type) {
        PromotedLaneDomainModel.Type.Seasonal -> PromotedLaneUiModel.Type.Seasonal
        PromotedLaneDomainModel.Type.Vegetarian -> PromotedLaneUiModel.Type.Vegetarian
        PromotedLaneDomainModel.Type.Vegan -> PromotedLaneUiModel.Type.Vegan
    }
}
