package com.team23.data.mappers

import com.team23.data.NEURACR_WEBSITE_HOME_URL

class ImageMapper {
	fun toImageUrl(rawImage: String): String = when {
		rawImage.startsWith("/assets/images/") -> "${NEURACR_WEBSITE_HOME_URL}$rawImage"
		else -> rawImage
	}
}
