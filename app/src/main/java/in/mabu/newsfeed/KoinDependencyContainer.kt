package `in`.mabu.newsfeed

import `in`.mabu.newsfeed.domain.FeedApis
import `in`.mabu.newsfeed.domain.FeedRepository
import `in`.mabu.newsfeed.domain.FeedRepositoryImpl
import `in`.mabu.newsfeed.network.NetworkManager
import `in`.mabu.newsfeed.viewmodel.FeedListViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val IO_DISPATCHERS = "IO_DISPATCHERS"
const val MAIN_DISPATCHERS = "MAIN_DISPATCHERS"
const val DEFAULT_DISPATCHERS = "DEFAULT_DISPATCHERS"

val repositoryModule = module {
    single<FeedRepository> { FeedRepositoryImpl(get(), get(), get(named(IO_DISPATCHERS))) }
}

val coroutineModule = module {
    single(named(IO_DISPATCHERS)) { Dispatchers.IO }
    single<CoroutineDispatcher>(named(MAIN_DISPATCHERS)) { Dispatchers.Main }
    single(named(DEFAULT_DISPATCHERS)) { Dispatchers.Default }
}

val viewModelModule = module {
    viewModel { FeedListViewModel(get()) }
}

val networkModule = module {
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(FeedApis::class.java) }
    single { NetworkManager() }
}