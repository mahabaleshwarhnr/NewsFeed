package `in`.mabu.newsfeed.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NetworkManager {

    private suspend fun <T> fetchInternal(apiCall: suspend () -> T): DataFetchState<T> {
        return try {
            Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    Error(ErrorResponse(throwable.message))
                }
                is HttpException -> {
                    Error(ErrorResponse(throwable.message))
                }
                else -> {
                    Error(ErrorResponse(throwable.message))
                }
            }
        }
    }

    fun <T> fetch(apiCall: suspend () -> T): Flow<DataFetchState<T>> {
       return flow {
            emit(Loading(true))
            val response = fetchInternal (apiCall)
           emit(Loading(false))
           emit(response)
        }
    }
}