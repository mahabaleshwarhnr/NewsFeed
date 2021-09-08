package `in`.mabu.newsfeed.view

import `in`.mabu.newsfeed.*
import `in`.mabu.newsfeed.network.Error
import `in`.mabu.newsfeed.network.Loading
import `in`.mabu.newsfeed.network.Success
import `in`.mabu.newsfeed.viewmodel.FeedListViewModel
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedListActivity : AppCompatActivity() {

    private val feedListViewModel: FeedListViewModel by viewModel()

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main).also {
            progressBar = findViewById(R.id.progress_bar)
            textView = findViewById(R.id.textView)
        }
        feedListViewModel.feedLiveData.observe(this) {
            when(it) {
                is Loading -> {
                    progressBar.isVisible = it.isLoading
                    textView.isVisible = !it.isLoading
                }
                is Error -> textView.text = it.errorResponse.errorMessage
                is Success -> textView.text = "Success"
            }
        }
    }
}