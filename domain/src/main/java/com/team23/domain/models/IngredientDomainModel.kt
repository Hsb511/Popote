package com.team23.domain.models

sealed class IngredientDomainModel(open val label: String) {

    data class WithoutQuantity(override val label: String) : IngredientDomainModel(label)

    sealed class WithQuantity(
        override val label: String,
        open val quantity: Float
    ) : IngredientDomainModel(label) {

        data class WithoutUnit(
            override val label: String,
            override val quantity: Float,
        ) : WithQuantity(label, quantity)

        data class WithUnit(
            override val label: String,
            override val quantity: Float,
            val unit: String,
        ) : WithQuantity(label, quantity)
    }
}