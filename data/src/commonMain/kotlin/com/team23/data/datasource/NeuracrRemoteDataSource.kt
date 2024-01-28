package com.team23.data.datasource

import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.select.Elements
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

internal class NeuracrWebsiteDataSource(
    private val httpClient: HttpClient,
) {

    suspend fun getLatestPostsFromHome(): Elements = Ksoup
        .parse(html = httpClient.get("").bodyAsText())
        .select("ul.latest-recipes")
        .select("li")

    suspend fun getRecipeById(recipeId: String): Elements = Ksoup
        .parse(html = httpClient.get(recipeId).bodyAsText())
        .select("div.main__content")
}
