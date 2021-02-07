package com.domain.task.news.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.domain.task.R
import com.domain.task.core.ui.BaseFragment
import com.domain.task.core.ui.views.loadImageFromUrl
import com.domain.task.core.utils.DateUtils
import com.domain.task.news.data.models.News
import com.domain.task.news.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_details.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DetailsFragment : BaseFragment() {

    companion object {
        val TAG = "DetailsFragment"
    }


    val args: DetailsFragmentArgs by navArgs()

    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(
            inflater,
            inflater.inflate(R.layout.fragment_details, container, false) as ViewGroup,
            savedInstanceState
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = injectViewModel()
        Log.d(BookMarkFragment.TAG, "onActivityCreated $viewModel")

        viewModel.loadNewsDetails(args.newsId).observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                it.data?.let { news ->
                    displayInfo(news)

                }

            }

        })
    }

    private fun displayInfo(news: News) {
        title.text = news.title;
        abs.text = news.abs;
        byline.text = news.byline;
        releaseDate.text = DateUtils.formatDate(news.createdDate);
        bookmark.isSelected = news.is_bookmark
        source.text = getString(R.string.source, news.shortUrl)
        source.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(news.shortUrl)
            startActivity(intent)
        }
        source
        bookmark.setOnClickListener {
            viewModel.addToBookMark(news.id, !news.is_bookmark)
        }
        try {
            thumbnail.loadImageFromUrl(news.multimedia[0].url)
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }


}