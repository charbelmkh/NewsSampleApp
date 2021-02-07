package com.domain.task.news.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.domain.task.R
import com.domain.task.core.ui.BaseFragment
import com.domain.task.core.ui.views.hide
import com.domain.task.core.ui.views.show
import com.domain.task.news.data.models.News
import com.domain.task.news.ui.adapters.NewsAdapter
import com.domain.task.news.ui.adapters.OnItemInterAction
import com.domain.task.news.ui.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.recycle_list_view.*

class BookMarkFragment : BaseFragment(), OnItemInterAction {

    companion object {
        const val TAG = "BookMarkFragment"

    }

    private lateinit var viewModel: NewsViewModel


    val adapter: NewsAdapter by lazy {
        NewsAdapter(
            this,R.layout.grid_news_list_item,

        )
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel()
        Log.d(TAG, "onActivityCreated $viewModel")
        viewModel.bookMarks.observe(viewLifecycleOwner, { result ->
            Log.d(TAG, "List Size: $result")
            result?.let {
                if (it.data == null || it.data.isEmpty()) {
                    adapter.submitList(null)
                    text_message.show()
                    text_message.text = getText(R.string.no_bookmarks_have_been_added)
                } else {
                    adapter.submitList(it.data)
                    text_message.hide()
                }
            }


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
        swipeToRefresh.isEnabled = false
        swipeToRefresh.isRefreshing = false
    }

    private fun setupList() {
        listView.adapter = adapter
        listView.layoutManager = GridLayoutManager(requireContext(), 2)
    }


    override fun onRowClicked(rowData: News) {
        val action = BookMarkFragmentDirections.fromBookmarkToDetails(rowData.id)
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onAddToBookMarkClick(rowData: News) {
        viewModel.addToBookMark(rowData.id, !rowData.is_bookmark)
    }

}