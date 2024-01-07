package com.team23.neuracrsrecipes.extension

inline fun <reified T : Enum<T>> T.next(): T {
	val values = enumValues<T>()
	val nextOrdinal = (ordinal + 1) % values.size
	return values[nextOrdinal]
}
