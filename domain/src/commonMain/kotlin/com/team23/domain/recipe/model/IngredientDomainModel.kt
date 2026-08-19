package com.team23.domain.recipe.model

sealed class IngredientDomainModel(open val label: String) {

    data class WithoutQuantity(override val label: String) : IngredientDomainModel(label) {
        fun shouldMergeWith(other: IngredientDomainModel): Boolean =
            other is WithoutQuantity && other.label.equals(label, ignoreCase = true)
    }

    sealed class WithQuantity(
        override val label: String,
        open val quantity: Float
    ) : IngredientDomainModel(label) {

        data class WithoutUnit(
            override val label: String,
            override val quantity: Float,
        ) : WithQuantity(label, quantity) {
            fun shouldMergeWith(other: IngredientDomainModel): Boolean =
                other is WithoutUnit && other.label.equals(label, ignoreCase = true)
        }

        data class WithUnit(
            override val label: String,
            override val quantity: Float,
            val unit: String,
        ) : WithQuantity(label, quantity) {
            fun shouldMergeWith(other: IngredientDomainModel): Boolean =
                other is WithUnit && other.label.equals(label, ignoreCase = true) && other.unit == unit
        }
    }
}