package com.team23.data.extensions

internal fun Long.toBoolean(): Boolean = when(this) {
    0L -> false
    1L -> true
    else -> throw IllegalArgumentException()
}
