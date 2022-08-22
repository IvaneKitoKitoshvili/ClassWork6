package com.kito.classwork6.data
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class UserInfo(
    @Json(name = "data")
    val `data`: List<Data?>?,
    @Json(name = "page")
    val page: Int?,
    @Json(name = "per_page")
    val perPage: Int?,
    @Json(name = "support")
    val support: Support?,
    @Json(name = "total")
    val total: Int?,
    @Json(name = "total_pages")
    val totalPages: Int?
)

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "color")
    val color: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "pantone_value")
    val pantoneValue: String?,
    @Json(name = "year")
    val year: Int?
)

@JsonClass(generateAdapter = true)
data class Support(
    @Json(name = "text")
    val text: String?,
    @Json(name = "url")
    val url: String?
)