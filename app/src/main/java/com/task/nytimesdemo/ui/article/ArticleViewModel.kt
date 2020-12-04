package com.task.nytimesdemo.ui.article

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.task.nytimesdemo.api.NYTimesApi
import com.task.nytimesdemo.data.NYTimesRepository
import com.task.nytimesdemo.util.Constants

class ArticleViewModel @ViewModelInject constructor(
    private val repository: NYTimesRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {


    val articles = repository.getMostPopularArticles(
        Constants.CURRENT_SECTION,
        Constants.DEFAULT_PERIOD_WEEKLY,
        Constants.API_KEY
    ).cachedIn(viewModelScope)


}