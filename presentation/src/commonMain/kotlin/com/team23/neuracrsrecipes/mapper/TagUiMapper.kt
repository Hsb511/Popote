package com.team23.neuracrsrecipes.mapper

import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.neuracrsrecipes.model.uimodel.TagUiModel

class TagUiMapper {

    fun toTagUiModels(tags: List<String>): List<TagUiModel> = tags.map { tagString ->
        TagUiModel(
            label = tagString,
            isSelected = false,
        )
    }

    fun toFlagProperty(tags: List<String>): FlagProperty? {
        return if ("french" in tags || "francais" in tags || "français" in tags) {
            FlagProperty.FRENCH
        } else if ("american" in tags || "américain" in tags || "americain" in tags) {
            FlagProperty.US
        } else if ("italian" in tags || "italien" in tags) {
            FlagProperty.ITALIAN
        } else if ("indian" in tags || "indien" in tags) {
            FlagProperty.INDIAN
        } else if ("alsatian" in tags || "alsacien" in tags) {
            FlagProperty.ALSATIAN
        } else if ("norman" in tags || "normand" in tags || "normandy" in tags || "normandie" in tags) {
            FlagProperty.NORMAN
        } else if ("thai" in tags || "thaïlandais" in tags || "thailandais" in tags) {
            FlagProperty.THAI
        } else if ("hungarian" in tags || "hongrois" in tags) {
            FlagProperty.HUNGARIAN
        } else if ("tex-mex" in tags) {
            FlagProperty.US_MEXICAN
        } else if ("chinese" in tags || "chinois" in tags) {
            FlagProperty.CHINESE
        } else if ("turkish" in tags || "turc" in tags) {
            FlagProperty.TURKISH
        } else {
            null
        }
    }
}
