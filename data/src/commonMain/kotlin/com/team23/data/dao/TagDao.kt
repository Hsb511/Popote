package com.team23.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.team23.data.models.TagDataModel
import data.AppDatabaseQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

internal class TagDao(
    private val dbQueries: AppDatabaseQueries,
) {

    fun insertOrReplace(tagDataModels: List<TagDataModel>) {
        tagDataModels
            .map { it.toDbModel() }
            .forEach { tag ->
                dbQueries.insertTag(
                    recipeId = tag.recipeId,
                    label = tag.label,
                )
            }
    }

    fun deleteAllByRecipeId(recipeId: String) {
        dbQueries.deleteTagByRecipeId(recipeId)
    }

    fun loadAll(): List<TagDataModel> = dbQueries.selectAllTags().executeAsList().map(::toDataModel)

    fun getTagsByRecipeId(recipeId: String): List<TagDataModel> =
        dbQueries.selectTagsByRecipeId(recipeId).executeAsList().map(::toDataModel)

    fun getRecipeIdByLabel(tagLabels: List<String>): Flow<List<String>> =
        dbQueries.selectTagRecipeIdByLabel(tagLabels).asFlow().mapToList(Dispatchers.IO)

    private fun TagDataModel.toDbModel() = data.TagDataModel(
        id = id, recipeId = recipeId, label = label,
    )

    private fun toDataModel(tagDataModel: data.TagDataModel) = TagDataModel(
        id = tagDataModel.id, recipeId = tagDataModel.recipeId, label = tagDataModel.label,
    )
}
