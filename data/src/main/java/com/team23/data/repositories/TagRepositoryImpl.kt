package com.team23.data.repositories

import com.team23.data.daos.TagDao
import com.team23.data.mappers.TagMapper
import com.team23.domain.tag.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
	private val tagDao: TagDao,
	private val tagMapper: TagMapper,
) : TagRepository {
	override suspend fun getAllTags(): List<String> = tagMapper.toTagDomainModel(tagDao.loadAll())
	override fun getRecipeIdByTags(tagsList: List<String>): Flow<List<String>> =
		tagDao.getRecipeIdByLabel(tagsList.flatMap { tagMapper.translateBackToEnglish(it) })
}
