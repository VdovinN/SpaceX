package com.vdovin.spacex.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "space_x")
data class SpaceX(
        @PrimaryKey
        val flightNumber: Long,
        val missionName: String?,
        val details: String?,
        val launchDate: String?,
        val rocketName: String?,
        val payloadMass: Double?,
        val wikipediaLink: String?,
        val youtubeVideoId: String?
)