package id.rafli.baseproject.ui.newsDetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import id.rafli.baseproject.R
import id.rafli.baseproject.base.App
import id.rafli.baseproject.base.BaseActivity
import id.rafli.baseproject.databinding.ActivityNewsDetailBinding
import id.rafli.baseproject.extention.getTimeAgo
import id.rafli.baseproject.extention.loadImage
import id.rafli.baseproject.extention.parseHtml
import id.rafli.baseproject.helper.Config.extra_id
import id.rafli.baseproject.helper.ImageDialog
import id.rafli.baseproject.helper.ViewModelObserver
import id.rafli.baseproject.helper.viewBinding
import id.rafli.baseproject.model.News

class NewsDetailActivity : BaseActivity() {
    private val binding by viewBinding(ActivityNewsDetailBinding::inflate)
    private var id = ""
    private var news: News? = null
    private val viewModel: DetailNewsViewModel by lazy {
        ViewModelProvider(this)[DetailNewsViewModel::class.java]
    }
    private lateinit var viewModelObserver: ViewModelObserver<News>

    override fun onDestroy() {
        super.onDestroy()
        viewModelObserver.removeObservers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setToolbar(getString(R.string.teks_informasi), binding.toolbar, isWhite = true)

        id = intent.getStringExtra(extra_id) ?: ""

        action()
        observeData()
    }

    private fun action() {
        binding.ivThumb.setOnClickListener {
            news?.let { it1 -> ImageDialog(this, it1.thumb).show() }
        }
    }

    private fun observeData() {
        //initialize
        viewModelObserver = ViewModelObserver(
            this,
            viewModel,
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
        viewModelObserver.observe()

        //call get data
        viewModel.getDetail(id)
    }

    private fun showData(data: News) {
        news = data
        binding.tvDate.text = data.createdAt.getTimeAgo()
        data.news_category?.let {
            binding.tvCategory.text = it.title
        } ?: run {
            binding.tvCategory.text = "-"
        }
        binding.tvTitle.text = data.title
        binding.tvDescription.text = data.content.parseHtml()
        binding.ivThumb.loadImage(data.thumb)
        binding.tvViews.text = getString(R.string.teks_views, data.views)

    }

}