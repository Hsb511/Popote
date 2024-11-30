package com.team23.neuracrsrecipes.extension

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual fun getLocalLanguage(): String = NSLocale.currentLocale.languageCode
