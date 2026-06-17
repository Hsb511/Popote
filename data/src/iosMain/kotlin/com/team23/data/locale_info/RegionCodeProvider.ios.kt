package com.team23.data.locale_info

import platform.Foundation.NSLocale
import platform.Foundation.countryCode
import platform.Foundation.currentLocale

actual fun currentRegionCode(): String? = NSLocale.currentLocale().countryCode
