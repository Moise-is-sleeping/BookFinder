package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val description: Description,
    val links: List<Link>,
    val title: String,
    val covers: List<Int>,
    val subject_places: List<String>,
    @SerialName("first_publish_date") val firstPublishDate: String,
    val subject_people: List<String>,
    val key: String,
    val authors: List<AuthorInfo>,
    val excerpts: List<Excerpt>,
    val subjects: List<String>,
    val type: Type,
    @SerialName("subject_times") val subjectTimes: List<String>,
    @SerialName("latest_revision") val latestRevision: Int,
    val revision: Int,
    val created: Created,
    @SerialName("last_modified") val lastModified: LastModified
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