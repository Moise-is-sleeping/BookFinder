package data

import com.google.gson.annotations.SerializedName

data class Response(
    val start: Int,
    @SerializedName("num_found")
    val numFound: Int,
    val docs: List<Document>
)

data class Document(
    @SerializedName("cover_i")
    val coverI: Int,
    @SerializedName("has_fulltext")
    val hasFulltext: Boolean,
    @SerializedName("edition_count")
    val editionCount: Int,
    val title: String,
    @SerializedName("author_name")
    val authorName: List<String>,
    @SerializedName("first_publish_year")
    val firstPublishYear: Int,
    val key: String,
    val ia: List<String>,
    @SerializedName("author_key")
    val authorKey: List<String>,
    @SerializedName("public_scan_b")
    val publicScanB: Boolean
)