package com.d108.sduty_admin.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.d108.sduty_admin.common.ApplicationClass
import com.d108.sduty_admin.model.api.AdminApi
import com.d108.sduty_admin.model.paging.StoryDataSource

class Repository private constructor(){

    private val retrofit = ApplicationClass.retrofit

    private val adminApi: AdminApi = retrofit.create(AdminApi::class.java)


    fun getAllReportStory() = Pager(
        config = PagingConfig(pageSize = 1, maxSize = 10, enablePlaceholders = false),
        pagingSourceFactory = {StoryDataSource(adminApi)}
    ).flow

    suspend fun getTimelineDetail(storySeq: Int) = adminApi.getTimelineDetail(storySeq, 0)

    suspend fun getJobList() = adminApi.getJobList()

    suspend fun deleteStory(storySeq: Int) = adminApi.deleteStory(storySeq)

    suspend fun deleteReply(replySeq: Int)  = adminApi.deleteReply(replySeq)



    companion object{
        private var instance: Repository? = null
        fun get(): Repository{
            if(instance == null){
                instance = Repository()
            }
            return instance ?: throw IllegalStateException("Repository must be initialized")
        }
    }
}