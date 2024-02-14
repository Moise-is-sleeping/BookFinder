package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class Search(
    val numFound: Int,
    val start: Int,
    val numFoundExact: Boolean,
    val docs: List<Doc>
)

@Serializable
data class Doc(
    val key: String,
    val type: String,
    val seed: List<String>,
    val title: String,
    @SerialName("title_sort") val titleSort: String,
    @SerialName("title_suggest") val titleSuggest: String,
    @SerialName("edition_count") val editionCount: Int,
    @SerialName("edition_key") val editionKey: List<String>,
    @SerialName("publish_date") val publishDate: List<String>,
    @SerialName("publish_year") val publishYear: List<Int>,
    @SerialName("first_publish_year") val firstPublishYear: Int,
    @SerialName("number_of_pages_median") val numberOfPagesMedian: Int,
    val isbn: List<String>,
    @SerialName("last_modified_i") val lastModifiedI: Long,
    @SerialName("ebook_count_i") val ebookCountI: Int,
    @SerialName("ebook_access") val ebookAccess: String,
    @SerialName("has_fulltext") val hasFulltext: Boolean,
    @SerialName("public_scan_b") val publicScanB: Boolean,
    @SerialName("ratings_average") val ratingsAverage: Double,
    @SerialName("ratings_sortable") val ratingsSortable: Double,
    @SerialName("ratings_count") val ratingsCount: Int,
    @SerialName("ratings_count_1") val ratingsCount1: Int,
    @SerialName("ratings_count_2") val ratingsCount2: Int,
    @SerialName("ratings_count_3") val ratingsCount3: Int,
    @SerialName("ratings_count_4") val ratingsCount4: Int,
    @SerialName("ratings_count_5") val ratingsCount5: Int,
    @SerialName("readinglog_count") val readinglogCount: Int,
    @SerialName("want_to_read_count") val wantToReadCount: Int,
    @SerialName("currently_reading_count") val currentlyReadingCount: Int,
    @SerialName("already_read_count") val alreadyReadCount: Int,
    @SerialName("cover_edition_key") val coverEditionKey: String,
    @SerialName("cover_i") val coverI: Int,
    val publisher: List<String>,
    val language: List<String>,
    @SerialName("author_key") val authorKey: List<String>,
    @SerialName("author_name") val authorName: List<String>,
    val subject: List<String>,
    @SerialName("publisher_facet") val publisherFacet: List<String>,
    @SerialName("subject_facet") val subjectFacet: List<String>,
    @SerialName("_version_") val version: Long,
    @SerialName("author_facet") val authorFacet: List<String>,
    @SerialName("subject_key") val subjectKey: List<String>
)