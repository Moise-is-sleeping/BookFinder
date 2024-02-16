package ui.state

import data.Models.Doc
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchState(
    val docs: List<Doc> = emptyList(),
    @SerialName("num_found") val numFound: Int = -1,
)


