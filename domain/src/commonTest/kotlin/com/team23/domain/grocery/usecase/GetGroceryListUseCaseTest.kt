package com.team23.domain.grocery.usecase

import com.team23.domain.grocery.model.GroceryDomainModel
import com.team23.domain.grocery.repository.GroceryListRepository
import com.team23.domain.recipe.model.IngredientDomainModel
import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel.Source
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetGroceryListUseCaseTest {

    private val groceryListRepository: GroceryListRepository = mock()
    private val getGroceryListUseCase = GetGroceryListUseCase(groceryListRepository)

    @Test
    fun `Given no grocery recipes, When invoked then returns empty grocery list`() = runTest {
        // Given
        every {
            groceryListRepository.getGroceryListRecipes()
        } returns flowOf(emptyList())

        // When
        val result = getGroceryListUseCase.invoke().first()

        // Then
        assertTrue(result.recipes.isEmpty())
        assertTrue(result.ingredients.isEmpty())
    }

    @Test
    fun `Given grocery recipes, When invoked then keeps recipes in result`() = runTest {
        // Given
        val recipe = emptyGroceryRecipe()
        every { groceryListRepository.getGroceryListRecipes() } returns flowOf(listOf(recipe))

        // When
        val result = getGroceryListUseCase.invoke().first()

        // Then
        assertEquals(listOf(recipe), result.recipes)
    }

    @Test
    fun `Given ingredient without quantity, When servings change then quantity is not applied`() = runTest {
        // Given
        val ingredient = ingredientWithoutQuantity()
        val recipe = emptyGroceryRecipe(
            servingsNumber = 2,
            servingsAmount = 4,
            ingredients = listOf(ingredient),
        )

        every { groceryListRepository.getGroceryListRecipes() } returns flowOf(listOf(recipe))

        // When
        val result = getGroceryListUseCase.invoke().first()

        // Then
        assertEquals(ingredient, result.ingredients.single().ingredientDomainModel)
    }

    @Test
    fun `Given ingredient without unit, When servings are doubled then quantity is doubled`() = runTest {
        // Given
        val recipe = emptyGroceryRecipe(
            servingsNumber = 2,
            servingsAmount = 4,
            ingredients = listOf(ingredientWithoutUnit(quantity = 2f)),
        )

        every { groceryListRepository.getGroceryListRecipes() } returns flowOf(listOf(recipe))

        // When
        val result = getGroceryListUseCase.invoke().first()

        // Then
        assertEquals(ingredientWithoutUnit(quantity = 4f), result.ingredients.single().ingredientDomainModel)
    }

    @Test
    fun `Given ingredient with unit, When servings are doubled, Then quantity is doubled`() = runTest {
        // Given
        val recipe = emptyGroceryRecipe(
            servingsNumber = 2,
            servingsAmount = 4,
            ingredients = listOf(ingredientWithUnit(quantity = 100f)),
        )
        every { groceryListRepository.getGroceryListRecipes() } returns flowOf(listOf(recipe))

        // When
        val result = getGroceryListUseCase.invoke().first()

        // Then
        assertEquals(ingredientWithUnit(quantity = 200f), result.ingredients.single().ingredientDomainModel)
    }

    @Test
    fun `Given servings are reduced, Then ingredient quantity is reduced`() = runTest {
        // Given
        val recipe = emptyGroceryRecipe(
            servingsNumber = 4,
            servingsAmount = 2,
            ingredients = listOf(ingredientWithoutUnit(quantity = 4f))
        )

        every { groceryListRepository.getGroceryListRecipes() } returns flowOf(listOf(recipe))

        // When
        val result = getGroceryListUseCase.invoke().first()

        // Then
        assertEquals(ingredientWithoutUnit(quantity = 2f), result.ingredients.single().ingredientDomainModel)
    }

    @Test
    fun `Given duplicate ingredients without quantity, Then they are merged`() = runTest {
        // Given
        val recipe1 = emptyGroceryRecipe(ingredients = listOf(ingredientWithoutQuantity()))
        val recipe2 = emptyGroceryRecipe(ingredients = listOf(ingredientWithoutQuantity()))

        every { groceryListRepository.getGroceryListRecipes() } returns flowOf(listOf(recipe1, recipe2))

        // When
        val result = getGroceryListUseCase.invoke().first()

        // Then
        assertEquals(
            listOf(GroceryDomainModel.Ingredient(ingredientWithoutQuantity())),
            result.ingredients,
        )
    }

    @Test
    fun `Given duplicate ingredients without unit, Then quantities are summed`() = runTest {
        // Given
        val recipe1 = emptyGroceryRecipe(ingredients = listOf(ingredientWithoutUnit(quantity = 2f)))
        val second2 = emptyGroceryRecipe(ingredients = listOf(ingredientWithoutUnit(quantity = 3f)))

        every { groceryListRepository.getGroceryListRecipes() } returns flowOf(listOf(recipe1, second2))

        // When
        val result = getGroceryListUseCase.invoke().first()

        // Then
        assertEquals(ingredientWithoutUnit(quantity = 5f), result.ingredients.single().ingredientDomainModel)
    }

    @Test
    fun `Given duplicate ingredients with unit, Then they are not merged`() = runTest {
        // Given
        val recipe1 = emptyGroceryRecipe(ingredients = listOf(ingredientWithUnit(quantity = 100f)))
        val recipe2 = emptyGroceryRecipe(ingredients = listOf(ingredientWithUnit(quantity = 200f)))

        every { groceryListRepository.getGroceryListRecipes() } returns flowOf(listOf(recipe1, recipe2))

        // When
        val result = getGroceryListUseCase.invoke().first()

        // Then
        assertEquals(2, result.ingredients.size)
        assertEquals(ingredientWithUnit(quantity = 100f), result.ingredients[0].ingredientDomainModel)
        assertEquals(ingredientWithUnit(quantity = 200f), result.ingredients[1].ingredientDomainModel)
    }

    @Test
    fun `Given multiple recipes with different servings, Then quantities are scaled before being merged`() = runTest {
        // Given
        val recipe1 = emptyGroceryRecipe(
            servingsNumber = 2,
            servingsAmount = 4,
            ingredients = listOf(ingredientWithoutUnit(quantity = 2f)),
        )

        val recipe2 = emptyGroceryRecipe(
            servingsNumber = 4,
            servingsAmount = 2,
            ingredients = listOf(ingredientWithoutUnit(quantity = 4f)),
        )
        every { groceryListRepository.getGroceryListRecipes() } returns flowOf(listOf(recipe1, recipe2))

        // When
        val result = getGroceryListUseCase.invoke().first()

        // Then
        assertEquals(
            ingredientWithoutUnit(quantity = 6f),
            result.ingredients.single().ingredientDomainModel,
        )
    }

    @Test
    fun `Given ingredients are unsorted, Then ingredients are sorted alphabetically ignoring case`() = runTest {
        // Given
        val tomato = IngredientDomainModel.WithoutQuantity("Tomato")
        val apple = IngredientDomainModel.WithoutQuantity("apple")
        val banana = IngredientDomainModel.WithoutQuantity("Banana")

        val recipe = emptyGroceryRecipe(ingredients = listOf(tomato, apple, banana))
        every { groceryListRepository.getGroceryListRecipes() } returns flowOf(listOf(recipe))

        // When
        val result = getGroceryListUseCase.invoke().first()

        // Then
        assertEquals(
            listOf(apple, banana, tomato),
            result.ingredients.map { it.ingredientDomainModel },
        )
    }

    private fun ingredientWithoutQuantity() = IngredientDomainModel.WithoutQuantity("Salt")
    private fun ingredientWithoutUnit(quantity: Float = 2f) =
        IngredientDomainModel.WithQuantity.WithoutUnit(label = "Egg", quantity = quantity)

    private fun ingredientWithUnit(quantity: Float = 100f) =
        IngredientDomainModel.WithQuantity.WithUnit(label = "Flour", quantity = quantity, unit = "g")

    private fun emptyGroceryRecipe(
        servingsNumber: Int = 2,
        servingsAmount: Int = servingsNumber,
        ingredients: List<IngredientDomainModel> = emptyList(),
    ): GroceryDomainModel.Recipe {
        return GroceryDomainModel.Recipe(
            recipeDomainModel = mockedRecipe(
                servingsNumber = servingsNumber,
                ingredients = ingredients,
            ),
            servingsAmount = servingsAmount,
        )
    }

    private fun mockedRecipe(
        servingsNumber: Int,
        ingredients: List<IngredientDomainModel>,
    ) = RecipeDomainModel.Full(
        servingsNumber = servingsNumber,
        ingredients = ingredients,
        id = "mucius",
        title = "eius",
        imageUrl = "https://duckduckgo.com/?q=curabitur",
        date = LocalDate(year = 2023, month = 6, day = 1),
        language = LanguageDomainModel.ENGLISH,
        isFavorite = false,
        source = Source.Remote,
        isInGroceryList = false,
        author = "consectetur",
        tags = listOf(),
        startingText = "decore",
        instructions = listOf(),
        endingText = "qualisque",
        sections = listOf(),
    )
}
