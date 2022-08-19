package com.d108.sduty_admin.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.d108.sduty_admin.common.ApplicationClass
import com.d108.sduty_admin.model.api.AdminApi
import com.d108.sduty_admin.model.dto.Admin
import com.d108.sduty_admin.model.dto.Notice
import com.d108.sduty_admin.model.dto.Qna
import com.d108.sduty_admin.model.paging.StoryDataSource

class Repository private constructor(){

    private val retrofit = ApplicationClass.retrofit

    private val adminApi: AdminApi = retrofit.create(AdminApi::class.java)

    suspend fun login(admin: Admin) = adminApi.login(admin)

    suspend fun getReportStory(page: Int, pageSize: Int) = adminApi.getReportStory(page, pageSize)

    suspend fun getNoticeList() = adminApi.getNoticeList()

    suspend fun insertNotice(notice: Notice) = adminApi.insertNotice(notice)

    suspend fun updateNotice(notice: Notice, seq: Int) = adminApi.updateNotice(notice, seq)

    suspend fun deleteNotice(seq: Int) = adminApi.deleteNotice(seq)

    fun getAllReportStory() = Pager(
        config = PagingConfig(pageSize = 1, maxSize = 10, enablePlaceholders = false),
        pagingSourceFactory = {StoryDataSource(adminApi)}
    ).flow

    suspend fun getTimelineDetail(storySeq: Int) = adminApi.getTimelineDetail(storySeq, 0)

    suspend fun getJobList() = adminApi.getJobList()

    suspend fun deleteStory(storySeq: Int) = adminApi.deleteStory(storySeq)

    suspend fun deleteReply(replySeq: Int)  = adminApi.deleteReply(replySeq)

    suspend fun sendFCM(message: Map<String, String>) = adminApi.sendFCM(message)

    suspend fun getQnaList() = adminApi.getQna()

    suspend fun updateQna(qna: Qna) = adminApi.updateQna(qna)

    suspend fun sendFCMOne(userSeq: Int) = adminApi.sendFCMOne(userSeq)



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