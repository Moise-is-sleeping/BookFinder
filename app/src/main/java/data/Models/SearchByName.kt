package data.Models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class SearchByName(
    val docs: List<Doc>,
    @SerialName("num_found") val numFound: Int,
)

@Serializable
data class Doc(
    val key: String,
    val title :String,
    val first_publish_year :String
)