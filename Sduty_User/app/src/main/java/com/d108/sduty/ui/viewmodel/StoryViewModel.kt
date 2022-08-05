package com.d108.sduty.ui.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d108.sduty.model.Retrofit
import com.d108.sduty.model.dto.*
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink

private const val TAG ="StoryViewModel"
class StoryViewModel: ViewModel() {
    private val _storyList = MutableLiveData<MutableList<Story>>()
    val storyList: LiveData<MutableList<Story>>
        get() = _storyList

    private val _timelineList = MutableLiveData<MutableList<Timeline>>()
    val timelineList: LiveData<MutableList<Timeline>>
        get() = _timelineList
    fun getStoryListValue(userSeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.getStoryList(userSeq).let {
                if(it.isSuccessful && it.body() != null){
                    _timelineList.postValue(it.body() as MutableList<Timeline>)
                }else{
                    Log.d(TAG, "getStoryListValue: ${it.code()}")
                }
            }
        }
    }

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    fun insertStory(story: Story, bitmap: Bitmap){
        viewModelScope.launch(Dispatchers.IO){
            Log.d(TAG, "insertStory: ${story}")
            try {
                val bitmapRequestBody = bitmap?.let {
                    BitmapRequestBody(it)
                }

                var fileName = "story/" + System.currentTimeMillis().toString()+".png"
                story.imageSource = fileName
//                var requestBody: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
                var imageBody : MultipartBody.Part = MultipartBody.Part.createFormData("uploaded_file",fileName,bitmapRequestBody)
                val json = Gson().toJson(story)
                val storyBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
                val response = Retrofit.storyApi.insertStory(imageBody, storyBody)
                Log.d(TAG, "insertStory: ${response.code()}")
                if(response.isSuccessful && response.body() != null){

                    _storyList.postValue(response.body() as MutableList<Story>)
                }
            }catch (e: Exception){
                Log.d(TAG, "insertStory: ${e.message}")
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

    private val _commentList = MutableLiveData<MutableList<Reply>>()
    val commentList: LiveData<MutableList<Reply>>
        get() = _commentList
    fun getCommentListValue(storySeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.getReplyList(storySeq).let {
                if (it.isSuccessful && it.body() != null) {
                    _commentList.postValue(it.body() as MutableList<Reply>)
                }else{
                    Log.d(TAG, "getCommentListValue: ${it.code()}")
                }
            }
        }
    }

    fun insertComment(comment: Reply){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.insertComment(comment, comment.storySeq).let {
                if (it.isSuccessful && it.body() != null) {
                    _commentList.postValue(it.body() as MutableList<Reply>)
                }else{
                    Log.d(TAG, "insertComment: ${it.code()}")
                }
            }
        }
    }

    fun updateComment(comment: Reply){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.updateComment(comment, comment.storySeq).let {
                if (it.isSuccessful && it.body() != null) {
                    _commentList.postValue(it.body() as MutableList<Reply>)
                }else{
                    Log.d(TAG, "updateComment: ${it.code()}")
                }
            }
        }
    }

    fun deleteComment(comment: Reply){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.storyApi.deleteComment(comment.storySeq, comment.seq).let {
                if (it.isSuccessful && it.body() != null) {
                    _commentList.postValue(it.body() as MutableList<Reply>)
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

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile>
        get() = _profile
    fun getProfileValue(userSeq: Int){
        viewModelScope.launch(Dispatchers.IO){
            Retrofit.profileApi.getProfileValue(userSeq).let {
                if(it.isSuccessful && it.body() != null) {
                    _profile.postValue(it.body() as Profile)
                    Log.d(TAG, "getProfileValue: ${it.body()}")
                }else{
                    Log.d(TAG, "getProfileValue: ${it.code()}")
                }
            }
        }
    }

    inner class BitmapRequestBody(private val bitmap: Bitmap) : RequestBody() {
        override fun contentType(): MediaType = "image/*".toMediaType()
        override fun writeTo(sink: BufferedSink) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 99, sink.outputStream())
        }
    }







}