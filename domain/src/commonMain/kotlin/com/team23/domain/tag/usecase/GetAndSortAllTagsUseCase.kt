package com.team23.domain.tag.usecase

import com.team23.domain.recipe.model.TagDomainModel
import com.team23.domain.tag.repository.TagRepository

class GetAndSortAllTagsUseCase (
	private val tagRepository: TagRepository,
) {
	suspend fun invoke(): List<TagDomainModel> = tagRepository.getAllTags().distinct().sortedBy { it.localizedName }
}
