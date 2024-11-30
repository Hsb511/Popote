package com.team23.view.sample.property

import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.view.Res
import com.team23.view.bretzel

internal val resourceImagePreviewSample = ImageProperty.Resource(
    contentDescription = null,
    drawableResource = Res.drawable.bretzel,
)

internal val remoteImagePreviewSample = ImageProperty.Remote(
    contentDescription = null,
    url = "https://neuracr.github.io/assets/images/bretzel.jpg"
)
