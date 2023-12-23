package com.team23.neuracrsrecipes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform