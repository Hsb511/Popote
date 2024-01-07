package com.team23.neuracrsrecipes.extension

import java.util.Locale

actual fun getLocalLanguage(): String = Locale.getDefault().language
