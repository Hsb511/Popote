package com.team23.domain.repositories

interface TagRepository {
	suspend fun getAllTags(): List<String>
}
