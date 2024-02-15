package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val title: String,
    val key: String,
    val authors: List<AuthorInfo>,
    val description: String,
    val covers: List<Int>,
    val subject_places: List<String>,
    val subjects: List<String>,
    val links: List<Link>,
    val type: Type,
    val subject_people: List<String>,
    @SerialName("subject_times") val subjectTimes: List<String>,
    val location :String,
    @SerialName("latest_revision") val latestRevision: Int,
    val revision: Int,
    val created: Created,
    @SerialName("last_modified") val lastModified: LastModified,

    @SerialName("first_publish_date") val firstPublishDate: String,



    val excerpts: List<Excerpt>,







)

@Serializable
data class Description(
    val type: String,
    val value: String
)

@Serializable
data class Link(
    val url: String,
    val title: String,
    val type: Type
)

@Serializable
data class Type(
    val key: String
)

@Serializable
data class AuthorInfo(
    val author: Author,
    val type: Type
)

@Serializable
data class Author(
    val key: String
)

@Serializable
data class Excerpt(
    val excerpt: String,
    val pages: String,
    val author: Author
)

@Serializable
data class Created(
    val type: String,
    val value: String
)

@Serializable
data class LastModified(
    val type: String,
    val value: String
)