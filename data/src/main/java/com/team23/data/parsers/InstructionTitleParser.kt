package com.team23.data.parsers

import org.jsoup.select.Elements
import javax.inject.Inject

class InstructionTitleParser @Inject constructor() {
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