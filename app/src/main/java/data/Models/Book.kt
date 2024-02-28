package data.Models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import ui.state.BookState


data class Book(
    val title: String? = BookState.DEFAUTL_TITLE,
    val covers: List<Int>? = mutableListOf(0),
    val subjects: List<String>? = emptyList(),
    val links: List<Link>? = emptyList(),
    val type: Type? = Type(""),
    @Contextual val description: Any? = "",
    @Contextual  var authors:Any = "",
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



