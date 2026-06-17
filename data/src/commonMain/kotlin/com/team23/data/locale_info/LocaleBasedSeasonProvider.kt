package com.team23.data.locale_info

import com.team23.domain.locale_info.Season
import com.team23.domain.locale_info.SeasonProvider
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

internal class LocaleBasedSeasonProvider: SeasonProvider {

    private val timeZone: TimeZone = TimeZone.currentSystemDefault()

    @OptIn(ExperimentalTime::class)
    override fun currentSeason(): Season {
        val month = Clock.System.now().toLocalDateTime(timeZone).date.month.number
        val isNorthernHemisphere = isNorthernHemisphere(currentRegionCode())

        return seasonForMonth(month, isNorthernHemisphere)
    }

    private fun seasonForMonth(month: Int, isNorthernHemisphere: Boolean): Season {
        val northernSeason = when (month) {
            3, 4, 5 -> Season.Spring
            6, 7, 8 -> Season.Summer
            9, 10, 11 -> Season.Autumn
            else -> Season.Winter
        }

        if (isNorthernHemisphere) return northernSeason

        return when (northernSeason) {
            Season.Spring -> Season.Autumn
            Season.Summer -> Season.Winter
            Season.Autumn -> Season.Spring
            Season.Winter -> Season.Summer
        }
    }

    // Locale region is a heuristic; default to north when region is unknown.
    private fun isNorthernHemisphere(regionCode: String?): Boolean {
        val normalizedCode = regionCode?.uppercase() ?: return true
        return normalizedCode !in SOUTHERN_HEMISPHERE_REGION_CODES
    }

    private companion object {
        private val SOUTHERN_HEMISPHERE_REGION_CODES = setOf(
            "AQ", "AR", "AU", "BR", "BW", "CC", "CL", "CX", "FK", "FJ", "HM", "ID", "IO",
            "LS", "MG", "MU", "MW", "MZ", "NA", "NC", "NF", "NR", "NU", "NZ", "PE", "PG",
            "PN", "PY", "RE", "SH", "SB", "SC", "SZ", "TF", "TK", "TL", "TO", "TV", "UY",
            "VU", "WS", "ZA", "ZM", "ZW"
        )
    }
}
