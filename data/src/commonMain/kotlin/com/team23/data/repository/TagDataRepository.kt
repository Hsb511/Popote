package com.team23.data.repository

import com.team23.data.datasource.NeuracrLocalDataSource
import com.team23.data.mappers.TagMapper
import com.team23.domain.tag.repository.TagRepository
import kotlinx.coroutines.flow.Flow

internal class TagDataRepository(
    neuracrLocalDataSource: NeuracrLocalDataSource,
    private val tagMapper: TagMapper,
) : TagRepository {

    private val tagDao = neuracrLocalDataSource.tagDao

    override suspend fun getAllTags(): List<String> = tagMapper.toTagDomainModel(tagDao.loadAll())

    override fun getRecipeIdByTags(tagsList: List<String>): Flow<List<String>> =
        tagDao.getRecipeIdByLabel(tagsList.flatMap { tagMapper.translateBackToEnglish(it) })
}
