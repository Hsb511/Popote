package com.team23.data.datasources

import org.jsoup.Jsoup
import org.jsoup.select.Elements
import javax.inject.Inject

internal class NeuracrWebsiteDataSource @Inject constructor() {
    companion object {
        internal const val NEURACR_WEBSITE_HOME_URL = "https://neuracr.github.io"
    }

    fun getLatestPostsFromHome(): Elements = Jsoup
        .connect(NEURACR_WEBSITE_HOME_URL)
        .get()
        .select("ul.latest-recipes")
        .select("li")

    fun getRecipeById(recipeId: String): Elements = Jsoup
        .connect("$NEURACR_WEBSITE_HOME_URL$recipeId")
        .get()
        .select("div.main__content")
}
