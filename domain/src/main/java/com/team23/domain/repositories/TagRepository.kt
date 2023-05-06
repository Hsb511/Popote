package com.team23.domain.repositories

import kotlinx.coroutines.flow.Flow

interface TagRepository {
	suspend fun getAllTags(): List<String>

	fun getRecipeIdByTags(tagsList: List<String>): Flow<List<String>>
}
