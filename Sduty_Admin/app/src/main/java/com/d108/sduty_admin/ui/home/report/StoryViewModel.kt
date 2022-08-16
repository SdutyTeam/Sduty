package com.d108.sduty_admin.ui.home.report

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.d108.sduty_admin.common.ApplicationClass
import com.d108.sduty_admin.model.dto.JobHashtag
import com.d108.sduty_admin.model.dto.Reply
import com.d108.sduty_admin.model.dto.Story
import com.d108.sduty_admin.model.dto.Timeline
import com.d108.sduty_admin.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.collections.MutableList
import kotlin.collections.hashMapOf
import kotlin.collections.set

private const val TAG ="StoryViewModel"
class StoryViewModel: ViewModel() {
    private val repository = Repository.get()

    fun getPagingReportStory(): Flow<PagingData<Story>> {
        return repository.getAllReportStory().cachedIn(viewModelScope)
    }

    private val _timeLine = MutableLiveData<Timeline>()
    val timeLine: LiveData<Timeline>
        get() = _timeLine
    private val _replyList = MutableLiveData<MutableList<Reply>>()
    val replyList: LiveData<MutableList<Reply>>
        get() = _replyList
    fun getTimelineValue(storySeq: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTimelineDetail(storySeq).let { 
                if(it.isSuccessful && it.body() != null){
                    _timeLine.postValue(it.body())
                    _replyList.postValue((it.body() as Timeline).replies)
                }else{
                    Log.d(TAG, "getTimelineValue: ${it.code()}")
                }
                    
            }
        }
    }

    fun deleteStory(storySeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteStory(storySeq).let {
                if(it.isSuccessful){
                    Log.d(TAG, "deleteStory: success")
                }else{
                    Log.d(TAG, "deleteStory: ${it.code()}")
                }
            }
        }
    }

    fun deleteReply(replySeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteReply(replySeq).let {
                if(it.isSuccessful && it.body() != null){
                    _replyList.postValue(it.body())
                }else{
                    Log.d(TAG, "deleteStory: ${it.code()}")
                }
            }
        }
    }

    fun getJobListValue(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getJobList().let {
                if(it.isSuccessful && it.body() != null){
                    setTagMap(it.body() as MutableList<JobHashtag>)
                }else{
                    Log.d(TAG, "getJobListValue: ${it.code()}")
                }
            }
        }
    }

    private fun setTagMap(list: MutableList<JobHashtag>){
        val map = hashMapOf<Int, String>()
        for(item in list) {
            map[item.seq] = item.name
        }
        ApplicationClass.jobTagMap = map
    }


}