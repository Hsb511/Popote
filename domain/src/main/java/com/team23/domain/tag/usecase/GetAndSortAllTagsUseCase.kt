package com.team23.domain.tag.usecase

import com.team23.domain.tag.repository.TagRepository
import javax.inject.Inject

class GetAndSortAllTagsUseCase @Inject constructor(
	private val tagRepository: TagRepository,
) {
	suspend fun invoke(): List<String> =
		tagRepository.getAllTags().toSortedSet().toList()
}
