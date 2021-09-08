package `in`.mabu.newsfeed.domain
import com.google.gson.annotations.SerializedName


data class Feed(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)