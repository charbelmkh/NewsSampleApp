package com.domain.task.news.ui.adapters

import com.domain.task.news.data.models.News

interface OnItemInterAction {
    fun onRowClicked(rowData: News)
    fun onAddToBookMarkClick(rowData: News)
}