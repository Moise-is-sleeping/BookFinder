package ui.state

import data.Models.Link
import data.Models.Type
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


@Serializable
data class BookState(
    val title: String? = DEFAUTL_TITLE,
    val covers: List<Int>? = emptyList(),
    val subjects: List<String>? = emptyList(),
    val links: List<Link>? = emptyList(),
    val type: Type? = Type(""),
    @Contextual val description: Any? = "",
){
    companion object{
        const val DEFAUTL_TITLE = "none"
    }
}

