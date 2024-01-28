package com.team23.data.repository

import com.team23.domain.tag.repository.TagRepository
import kotlinx.coroutines.flow.Flow

internal class TagDataRepository : TagRepository {

    override suspend fun getAllTags(): List<String> {
        return listOf("indian", "chicken")
    }

    override fun getRecipeIdByTags(tagsList: List<String>): Flow<List<String>> {
        TODO("Not yet implemented")
    }
}