package com.example.dailypulse.source.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourcesResponse(
    @SerialName("status")
    val status: String,
    @SerialName("sources")
    val sources: List<SourceRaw>
)