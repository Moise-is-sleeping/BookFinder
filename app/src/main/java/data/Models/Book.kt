package data.Models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val title: String,
    val covers: List<Int>?,
    val subjects: List<String>?,
    val links: List<Link>?,
    val type: Type?,
    @Contextual val description: Any?,
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
