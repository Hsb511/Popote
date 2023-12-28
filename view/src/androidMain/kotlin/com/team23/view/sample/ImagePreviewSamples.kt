package com.team23.view.sample

import com.team23.neuracrsrecipes.model.property.ImageProperty

internal val resourceImagePreviewSample = ImageProperty.Resource(
    contentDescription = null,
    imageRes = "bretzel.jpg"
)

internal val remoteImagePreviewSample = ImageProperty.Remote(
    contentDescription = null,
    url = "https://neuracr.github.io/recipes/2022/03/06/bretzels.html"
)
