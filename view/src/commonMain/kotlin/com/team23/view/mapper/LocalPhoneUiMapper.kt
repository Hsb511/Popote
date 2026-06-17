package com.team23.view.mapper

import com.team23.neuracrsrecipes.model.action.CellAction
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.ColorProperty
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.Res
import com.team23.view.ic_local_smartphone
import com.team23.view.locally_saved_button_content_description

class LocalPhoneUiMapper {

    fun toLocalPhoneProperty(): CellProperty.LocalPhone =
        CellProperty.LocalPhone(
            iconProperty = IconProperty.Resource(
                drawableResource = Res.drawable.ic_local_smartphone,
                contentDescription = Res.string.locally_saved_button_content_description,
                tint = ColorProperty.DefaultIcon,
            ),
            action = CellAction.LocalPhoneClick,
        )
}
