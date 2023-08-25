package com.team23.data.mappers

import com.team23.data.datasources.NeuracrWebsiteDataSource
import javax.inject.Inject

class ImageMapper @Inject constructor() {
	fun toImageUrl(rawImage: String): String = when {
		rawImage.startsWith("/assets/images/") -> "${NeuracrWebsiteDataSource.NEURACR_WEBSITE_HOME_URL}$rawImage"
		else -> rawImage
	}
}