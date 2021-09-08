package `in`.mabu.newsfeed

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class NewsFeedApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsFeedApp)
            loadKoinModules(listOf(repositoryModule, coroutineModule, viewModelModule, networkModule))
        }
    }
}