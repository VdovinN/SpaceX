package com.vdovin.spacex.util

import com.vdovin.spacex.api.model.Space
import com.vdovin.spacex.database.model.SpaceX
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun Long.convertTimestampToFormattedDate(): String {
    val timestamp = this
    val date = Date(timestamp * 1000)
    val formatter = SimpleDateFormat("EEEE dd, yyyy", Locale.getDefault())
    return formatter.format(date)
}

fun String.getYoutubeId(): String {
    val link = this
    var youtubeVideoId = ""
    val m: Matcher = Pattern.compile("[?&;]v=([^?&;]+)").matcher(link)
    if (m.find()) {
        youtubeVideoId = m.group(1)
    }
    return youtubeVideoId
}

fun Space.convertToDatabaseModel() = SpaceX(
        flightNumber,
        missionName,
        details,
        launchDateUnix?.convertTimestampToFormattedDate(),
        rocket?.rocketName,
        rocket?.secondStage?.payloads?.get(0)?.payloadMassKg,
        links?.wikipedia,
        links?.videoLink?.getYoutubeId()
)