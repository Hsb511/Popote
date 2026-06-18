package com.team23.domain.tag.repository

import com.team23.domain.recipe.model.TagDomainModel
import kotlinx.coroutines.flow.Flow

interface TagRepository {
	suspend fun getAllTags(): List<TagDomainModel>

	fun getRecipeIdByTags(tagsList: List<String>): Flow<List<String>>
}
