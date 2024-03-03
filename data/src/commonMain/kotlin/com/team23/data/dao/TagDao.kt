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

    fun insertOrReplace(vararg tagDataModel: TagDataModel) {
        tagDataModel
            .toList()
            .map { it.toDbModel() }
            .forEach { dbQueries.insertTag(it) }
    }

    fun deleteAllByRecipeId(recipeId: String) {
        dbQueries.deleteTagByRecipeId(recipeId)
    }

    fun loadAll(): List<TagDataModel> = dbQueries.selectAllTags().executeAsList()
        .map { it.toDataModel() }

    fun getRecipeIdByLabel(tagLabels: List<String>): Flow<List<String>> =
        dbQueries.selectTagRecipeIdByLabel(tagLabels).asFlow().mapToList(Dispatchers.IO)

    private fun TagDataModel.toDbModel() = data.TagDataModel(
        id = id, recipeId = recipeId, label = label,
    )

    private fun data.TagDataModel.toDataModel() = TagDataModel(
        id = id, recipeId = recipeId, label = label,
    )
}
