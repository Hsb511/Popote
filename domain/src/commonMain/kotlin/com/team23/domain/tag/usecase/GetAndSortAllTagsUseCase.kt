package com.team23.domain.tag.usecase

import com.team23.domain.tag.repository.TagRepository

class GetAndSortAllTagsUseCase (
	//private val tagRepository: TagRepository,
) {
	suspend fun invoke(): List<String> = listOf("aaa", "nn", "23")
		// tagRepository.getAllTags().sorted()
}
