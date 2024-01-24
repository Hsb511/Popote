package com.team23.neuracrsrecipes.mapper

import com.team23.neuracrsrecipes.model.uimodel.TagUiModel

class TagUiMapper {

    fun toTagUiModels(tags: List<String>): List<TagUiModel> = tags.map { tagString ->
        TagUiModel(
            label = tagString,
            isSelected = false,
        )
    }
}
