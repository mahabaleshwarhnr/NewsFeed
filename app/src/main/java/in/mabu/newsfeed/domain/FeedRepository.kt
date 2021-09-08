package `in`.mabu.newsfeed.domain

import `in`.mabu.newsfeed.network.DataFetchState
import `in`.mabu.newsfeed.network.Success
import `in`.mabu.newsfeed.network.NetworkManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

interface FeedRepository {

    fun fetchFeeds(): Flow<DataFetchState<List<Feed>>>
}

class FeedRepositoryImpl(private val feedApis: FeedApis,
                         private val networkManager: NetworkManager,
                         private val coroutineDispatcher: CoroutineDispatcher): FeedRepository {

    override fun fetchFeeds(): Flow<DataFetchState<List<Feed>>> {
       return networkManager
           .fetch { feedApis.fetchFeeds() }
           .onEach {
               if (it is Success) {} // save to db
           }
           .flowOn(coroutineDispatcher)
    }
}