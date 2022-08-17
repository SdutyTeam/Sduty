package com.d108.sduty_admin.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.d108.sduty_admin.common.ApplicationClass
import com.d108.sduty_admin.model.api.AdminApi
import com.d108.sduty_admin.model.dto.Admin
import com.d108.sduty_admin.model.dto.Notice
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