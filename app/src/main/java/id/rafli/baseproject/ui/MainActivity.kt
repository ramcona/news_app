package id.rafli.baseproject.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.rafli.baseproject.R
import id.rafli.baseproject.adapter.NewsAdapter
import id.rafli.baseproject.base.App
import id.rafli.baseproject.base.BaseActivity
import id.rafli.baseproject.databinding.ActivityMainBinding
import id.rafli.baseproject.helper.ViewModelObserver
import id.rafli.baseproject.helper.viewBinding
import id.rafli.baseproject.model.News

class MainActivity : BaseActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val newsViewModel: NewsViewModel by lazy {
        ViewModelProvider(this)[NewsViewModel::class.java]
    }
    private lateinit var newsViewModelObserver: ViewModelObserver<List<News>>
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        newsViewModelObserver.removeObservers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        removeActionBar()
        setupUI()
        observeData()
    }

    private fun setupUI() {
        binding.rvData.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    private fun observeData() {

        //initialize
        newsViewModelObserver = ViewModelObserver(
            this,
            newsViewModel,
            { showLoading() }, //on loading
            { removeLoading() }, //fetch data from remote done
            { responseData -> //show remote data
                showData(responseData)
            },
            { errorMessage -> //show error when retrive remote data
                App().showSnackbar(binding.root, errorMessage)
            }
        )

        //start observer data
        newsViewModelObserver.observe()

        //call get data
        newsViewModel.getNews()
    }

    private fun showData(data: List<News>) {
        newsAdapter.addList(data)
    }
}