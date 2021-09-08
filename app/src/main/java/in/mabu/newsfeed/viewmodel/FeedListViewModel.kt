package `in`.mabu.newsfeed.viewmodel

import `in`.mabu.newsfeed.domain.FeedRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class FeedListViewModel(feedRepository: FeedRepository): ViewModel() {


    val feedLiveData = feedRepository.fetchFeeds().asLiveData()
}