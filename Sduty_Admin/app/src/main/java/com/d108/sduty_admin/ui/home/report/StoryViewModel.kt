package com.d108.sduty_admin.ui.home.report

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bumptech.glide.Glide.init
import com.d108.sduty_admin.model.dto.Story
import com.d108.sduty_admin.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StoryViewModel() : ViewModel() {
    private val repository = Repository.get()

    fun getPagingReportStory(): Flow<PagingData<Story>> {
        return repository.getAllReportStory().cachedIn(viewModelScope)
    }
}