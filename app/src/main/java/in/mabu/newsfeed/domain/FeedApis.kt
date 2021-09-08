package `in`.mabu.newsfeed.domain

import retrofit2.http.GET

interface FeedApis {

    @GET("posts")
    suspend fun fetchFeeds(): List<Feed>
}