package com.team23.domain.usecases

import com.team23.domain.repositories.TagRepository
import javax.inject.Inject

class GetAndSortAllTagsUseCase @Inject constructor(
	private val tagRepository: TagRepository,
) {
	suspend fun invoke(): List<String> =
		tagRepository.getAllTags().toSortedSet().toList()
}
