package com.domain.task.news.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "news")
data class News(

    @PrimaryKey @ColumnInfo(name = "id")
    @field:SerializedName("id") var id: Long,
    @field:SerializedName("section") val section: String,
    @field:SerializedName("subsection") val subsection: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("abstract") val abs: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("uri") val uri: String,
    @field:SerializedName("byline") val byline: String,
    @field:SerializedName("item_type") val itemType: String,
    @field:SerializedName("updated_date") val updatedDate: String,
    @field:SerializedName("created_date") val createdDate: String,
    @field:SerializedName("published_date") val publishedDate: String,
    @field:SerializedName("material_type_facet") val materialTypeFacet: String,
    @field:SerializedName("kicker") val kicker: String,
    @field:SerializedName("des_facet") val desFacet: List<String>,
    @field:SerializedName("org_facet") val orgFacet: List<String>,
    @field:SerializedName("per_facet") val perFacet: List<String>,
    @field:SerializedName("geo_facet") val geoFacet: List<String>,
    @field:SerializedName("multimedia") val multimedia: List<Multimedia>,
    @field:SerializedName("short_url") val shortUrl: String,
    @field:SerializedName("is_bookmark") val is_bookmark: Boolean


)

