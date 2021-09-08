package `in`.mabu.newsfeed.network

sealed class DataFetchState<T>

data class Loading<T>(val isLoading: Boolean): DataFetchState<T>()
data class Error<T>(val errorResponse: ErrorResponse): DataFetchState<T>()
data class Success<T>(val data: T?): DataFetchState<T>()
