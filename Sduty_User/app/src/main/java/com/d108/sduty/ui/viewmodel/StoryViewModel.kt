package com.d108.sduty.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.Comment
import com.d108.sduty.model.dto.Story
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG ="StoryViewModel"
class StoryViewModel: ViewModel() {
    private val _storyList = MutableLiveData<MutableList<Story>>()
    val storyList: LiveData<MutableList<Story>>
        get() = _storyList
    fun getStoryListValue(){
        viewModelScope.launch(Dispatchers.IO){

        }
    }

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    fun insertStory(story: Story){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.insertStory(story).let {
                if (it.isSuccessful && it.body() != null) {
                    _storyList.postValue(it.body() as MutableList<Story>)
                }else{
                    Log.d(TAG, "insertStory: ${it.code()}")
                }
            }
        }
    }

    private val _story = MutableLiveData<Story>()
    val story: LiveData<Story>
        get() = _story

    fun getStoryValue(storySeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.getStudyDetail(storySeq).let {
                if (it.isSuccessful && it.body() != null) {
                    _story.postValue(it.body() as Story)
                }else{
                    Log.d(TAG, "getStoryValue: ${it.code()}")
                }
            }
        }
    }

    private val _userStoryList = MutableLiveData<List<Story>>()
    val userStoryList: LiveData<List<Story>>
        get() = _userStoryList
    fun getUserStoryListValue(userSeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.getUserStoryList(userSeq).let {
                if (it.isSuccessful && it.body() != null) {
                    _userStoryList.postValue(it.body() as MutableList<Story>)
                }else{
                    Log.d(TAG, "getUserStoryListValue: ${it.code()}")
                }
            }
        }
    }

    private val _scrapStoryList = MutableLiveData<List<Story>>()
    val scrapStoryList: LiveData<List<Story>>
        get() = _scrapStoryList
    fun getScrapStoryListValue(userSeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.getScrapList(userSeq).let {
                if (it.isSuccessful && it.body() != null) {
                    _scrapStoryList.postValue(it.body() as MutableList<Story>)
                }else{
                    Log.d(TAG, "getScrapStoryListValue: ${it.code()}")
                }
            }
        }
    }

    private val _filteredStoryList = MutableLiveData<List<Story>>()
    val filteredStoryList: LiveData<List<Story>>
        get() = _filteredStoryList
    fun getFilteredStoryList(userSeq: Int, hashTag: String){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.getFilteredStoryList(userSeq, hashTag).let {
                if (it.isSuccessful && it.body() != null) {
                    _filteredStoryList.postValue(it.body() as MutableList<Story>)
                }else{
                    Log.d(TAG, "getFilteredStoryList: ${it.code()}")
                }
            }
        }
    }

    fun deleteStory(storySeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.deleteStory(storySeq).let {
                if (it.code() == 200) {

                }else if(it.code() == 401){

                }
                else{

                }
            }
        }
    }

    private val _commentList = MutableLiveData<MutableList<Comment>>()
    val commentList: LiveData<MutableList<Comment>>
        get() = _commentList
    fun getCommentListValue(storySeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.getReplyList(storySeq).let {
                if (it.isSuccessful && it.body() != null) {
                    _commentList.postValue(it.body() as MutableList<Comment>)
                }else{
                    Log.d(TAG, "getCommentListValue: ${it.code()}")
                }
            }
        }
    }

    fun insertComment(comment: Comment){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.insertComment(comment, comment.storySeq).let {
                if (it.isSuccessful && it.body() != null) {
                    _commentList.postValue(it.body() as MutableList<Comment>)
                }else{
                    Log.d(TAG, "insertComment: ${it.code()}")
                }
            }
        }
    }

    fun updateComment(comment: Comment){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.updateComment(comment, comment.storySeq).let {
                if (it.isSuccessful && it.body() != null) {
                    _commentList.postValue(it.body() as MutableList<Comment>)
                }else{
                    Log.d(TAG, "updateComment: ${it.code()}")
                }
            }
        }
    }

    fun deleteComment(comment: Comment){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.deleteComment(comment.storySeq, comment.seq).let {
                if (it.isSuccessful && it.body() != null) {
                    _commentList.postValue(it.body() as MutableList<Comment>)
                }else{
                    Log.d(TAG, "updateComment: ${it.code()}")
                }
            }
        }
    }

    fun likeStory(userSeq: Int, story: Story){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.likeStory(userSeq, story).let {
                if (it.code() == 200) {

                }else if(it.code() == 401){

                }
                else{

                }
            }
        }
    }

    fun reportStory(story: Story){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.reportStory(story).let {
                if (it.code() == 200) {

                }else if(it.code() == 401){

                }
                else{

                }
            }
        }
    }








}