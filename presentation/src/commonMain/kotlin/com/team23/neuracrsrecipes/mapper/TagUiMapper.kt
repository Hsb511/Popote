package com.team23.neuracrsrecipes.mapper

import com.team23.domain.recipe.model.TagDomainModel
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.neuracrsrecipes.model.uimodel.TagUiModel

class TagUiMapper {

    fun toTagUiModels(tags: List<TagDomainModel>): List<TagUiModel> = tags.map { tag ->
        TagUiModel(
            label = tag.localizedName,
            isSelected = false,
        )
    }

    fun toFlagProperty(tags: List<TagDomainModel>): FlagProperty? {
        val cuisineRegionTag = tags.filterIsInstance<TagDomainModel.CuisineRegion>().firstOrNull() ?: return null
        return when (cuisineRegionTag.region) {
            TagDomainModel.Region.AMERICAN -> FlagProperty.US
            TagDomainModel.Region.AMERICAN_MEXICAN -> FlagProperty.US_MEXICAN
            TagDomainModel.Region.ALSATIAN -> FlagProperty.ALSATIAN
            TagDomainModel.Region.CHINESE -> FlagProperty.CHINESE
            TagDomainModel.Region.FRENCH -> FlagProperty.FRENCH
            TagDomainModel.Region.HUNGARIAN -> FlagProperty.HUNGARIAN
            TagDomainModel.Region.ITALIAN -> FlagProperty.ITALIAN
            TagDomainModel.Region.INDIAN -> FlagProperty.INDIAN
            TagDomainModel.Region.NORMAN -> FlagProperty.NORMAN
            TagDomainModel.Region.THAI -> FlagProperty.THAI
            TagDomainModel.Region.TURKISH -> FlagProperty.TURKISH
        }
    }
}
