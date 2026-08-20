package com.team23.neuracrsrecipes.mapper

import com.team23.domain.recipe.model.TagDomainModel
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.neuracrsrecipes.model.uimodel.TagUiModel

class TagUiMapper {

    fun toTagUiModels(tags: List<TagDomainModel>): List<TagUiModel> = tags.map { tag ->
        when (tag){
            is TagDomainModel.CuisineRegion -> TagUiModel.Flag(
                label = tag.localizedName,
                isSelected = false,
                flagProperty = toFlagProperty(listOf(tag)) ?: FlagProperty.UK_US,
            )
            is TagDomainModel.Normal -> TagUiModel.Label(
                label = tag.localizedName,
                isSelected = false,
            )
        }
    }.sortedBy { if (it is TagUiModel.Flag) 0 else 1 }

    fun toFlagProperty(cuisineRegionTag: TagDomainModel.CuisineRegion): FlagProperty? {
        return when (cuisineRegionTag.region) {
            TagDomainModel.Region.AMERICAN -> FlagProperty.US
            TagDomainModel.Region.AMERICAN_MEXICAN -> FlagProperty.US_MEXICAN
            TagDomainModel.Region.ALSATIAN -> FlagProperty.ALSATIAN
            TagDomainModel.Region.BELGIAN -> FlagProperty.BELGIAN
            TagDomainModel.Region.CHINESE -> FlagProperty.CHINESE
            TagDomainModel.Region.CUBAN -> FlagProperty.CUBAN
            TagDomainModel.Region.FRENCH -> FlagProperty.FRENCH
            TagDomainModel.Region.GREEK -> FlagProperty.GREEK
            TagDomainModel.Region.HUNGARIAN -> FlagProperty.HUNGARIAN
            TagDomainModel.Region.ITALIAN -> FlagProperty.ITALIAN
            TagDomainModel.Region.INDIAN -> FlagProperty.INDIAN
            TagDomainModel.Region.LEBANESE -> FlagProperty.LEBANESE
            TagDomainModel.Region.NORMAN -> FlagProperty.NORMAN
            TagDomainModel.Region.PUERTO_RICAN -> FlagProperty.PUERTO_RICAN
            TagDomainModel.Region.THAI -> FlagProperty.THAI
            TagDomainModel.Region.TURKISH -> FlagProperty.TURKISH
        }
    }

    fun toFlagProperty(tags: List<TagDomainModel>): FlagProperty? {
        val cuisineRegionTag = tags.filterIsInstance<TagDomainModel.CuisineRegion>().firstOrNull() ?: return null
        return toFlagProperty(cuisineRegionTag)
    }
}
