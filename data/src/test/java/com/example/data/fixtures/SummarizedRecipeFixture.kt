package com.team23.data.fixtures

import com.team23.data.models.SummarizedRecipeDataModel
import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import java.time.LocalDate

internal fun getDefaultSummarizedRecipe(
    href: String = "",
    imgSrc: String = "",
    title: String = ""
) = SummarizedRecipeDataModel(
    href = href,
    imgSrc = imgSrc,
    title = title,
)

internal fun getFirstSummarizedRecipeFromRawHtml() = getDefaultSummarizedRecipe(
    href = "/recipes/2022/12/19/zucchini_boursin_soup_fr.html",
    imgSrc = "/assets/images/zucchini_boursin_soup.jpg",
    title = "Soupe de courgettes et boursin"
)

internal fun getSecondSummarizedRecipeFromRawHtml() = getDefaultSummarizedRecipe(
    href = "/recipes/2022/12/04/carrot_curry_soup_fr.html",
    imgSrc = "/assets/images/carrot_curry_soup.jpg",
    title = "Soupe de carottes au curry"
)

internal fun getFirstLocalDate() = LocalDate.of(2022, 12, 19)
internal fun getSecondLocalDate() = LocalDate.of(2022, 12, 4)

internal fun getFirstSummarizedRecipeDomainModel() = RecipeDomainModel.Summarized(
    id = "/recipes/2022/12/19/zucchini_boursin_soup_fr.html",
    title = "Soupe de courgettes et boursin",
    imageUrl = "https://neuracr.github.io/assets/images/zucchini_boursin_soup.jpg",
    date = getFirstLocalDate(),
    language = LanguageDomainModel.FRENCH,
)

internal fun getSecondSummarizedRecipeDomainModel() = RecipeDomainModel.Summarized(
    id = "/recipes/2022/12/04/carrot_curry_soup_fr.html",
    title = "Soupe de carottes au curry",
    imageUrl = "https://neuracr.github.io/assets/images/carrot_curry_soup.jpg",
    date = getSecondLocalDate(),
    language = LanguageDomainModel.FRENCH,
)

internal val rawHtmlToParse = """
    <ul class="latest-recipes">
        
        <li>
            <div class="recipe-preview-tile">
                <a href="/recipes/2022/12/19/zucchini_boursin_soup_fr.html">
                    <img src="/assets/images/zucchini_boursin_soup.jpg" alt="recipe tile preview picture" class="image">
                    <div class="recipe-preview-tile__title-shadow">Soupe de courgettes et boursin</div>
                    <div class="recipe-preview-tile__title">Soupe de courgettes et boursin</div>
                </a>
            </div>
        </li>
        
        <li>
            <div class="recipe-preview-tile">
                <a href="/recipes/2022/12/04/carrot_curry_soup_fr.html">
                    <img src="/assets/images/carrot_curry_soup.jpg" alt="recipe tile preview picture" class="image">
                    <div class="recipe-preview-tile__title-shadow">Soupe de carottes au curry</div>
                    <div class="recipe-preview-tile__title">Soupe de carottes au curry</div>
                </a>
            </div>
        </li>
        
    </ul>
""".trimIndent()

