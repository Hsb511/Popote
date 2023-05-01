package com.team23.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SummarizedRecipeDataModel(
    @PrimaryKey val href: String,
    val imgSrc: String,
    val title: String,
)
