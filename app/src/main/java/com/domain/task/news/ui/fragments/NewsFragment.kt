package com.domain.task.news.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.navigation.fragment.NavHostFragment
import com.domain.task.R
import com.domain.task.core.ui.BaseFragment
import com.domain.task.news.data.models.News
import com.domain.task.news.ui.adapters.NewsAdapter
import com.domain.task.news.ui.adapters.OnItemInterAction
import com.domain.task.news.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.recycle_list_view.*

class NewsFragment : BaseFragment(), OnItemInterAction {

    companion object {
        const val TAG = "NewsFragment"

    }

    private lateinit var viewModel: NewsViewModel


    val adapter: NewsAdapter by lazy {
        NewsAdapter(
            this, R.layout.news_list_item,

            )
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel()
        Log.d(BookMarkFragment.TAG, "onActivityCreated $viewModel")
        viewModel.newsLiveData.observe(viewLifecycleOwner, { result ->
            Log.d(TAG, "List Size: $result")
            handleLiveDataResults(result,
                success = {
                    if (it == null || it.isEmpty()) {
                        showSnackBarWithMessage(R.string.no_data_found)
                    } else {
                        adapter.submitList(it)
                    }
                },
                retry = { loadNews() }
            )

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return super.onCreateView(
            inflater,
            inflater.inflate(R.layout.fragment_news_list, container, false) as ViewGroup,
            savedInstanceState
        )
    }


    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()

    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewStateRestored")
        super.onViewStateRestored(savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "onSaveInstanceState")
        super.onSaveInstanceState(outState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupPullDownToRefresh()
    }

    private fun setupPullDownToRefresh() {
        swipeToRefresh.setOnRefreshListener {
            swipeToRefresh.isRefreshing = false
            loadNews()
        }
    }

    private fun setupList() {
        listView.adapter = adapter
    }


    override fun onRowClicked(rowData: News) {
        val action = NewsFragmentDirections.fromNewsToDetails(rowData.id)
        NavHostFragment.findNavController(this).navigate(action)

    }

    override fun onAddToBookMarkClick(rowData: News) {
        viewModel.addToBookMark(rowData.id, !rowData.is_bookmark)
    }


    private fun loadNews() {
        snackBar.dismiss()
        viewModel.fetchTopNews()
    }
}