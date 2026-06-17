package com.team23.data.locale_info

import java.util.Locale

actual fun currentRegionCode(): String? = Locale.getDefault().country.takeIf { it.isNotBlank() }
