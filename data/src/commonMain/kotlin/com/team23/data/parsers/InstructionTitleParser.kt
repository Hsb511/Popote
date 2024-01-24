package com.team23.data.parsers

import com.fleeksoft.ksoup.select.Elements

internal class InstructionTitleParser {
    fun toCleanInstructionTitle(rawRecipe: Elements) = rawRecipe
        .select("section.recipe__instructions")
        .html()
        .split("<h2>")
        .first()
        .split("<br>")
        .first()
        .replace("<!--end excerpt--> ", "\n")
        .replace("<!--end excerpt-->", "\n")
        .replace("<b>", "")
        .replace("</b>", "")
}
