package com.d108.sduty_admin.model.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.d108.sduty_admin.model.api.AdminApi
import com.d108.sduty_admin.model.dto.Story

private const val TAG ="TimeLineDataSource"
class StoryDataSource(private val adminApi: AdminApi): PagingSource<Int, Story>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> {
        return try{
            val page = params.key?: 0
            val response = adminApi.getReportStory(page, 18)
            val body = response.body() as PagingResult<Story>
            Log.d(TAG, "load: ${body.result} ")
            if(response.isSuccessful && body.result.isNotEmpty()) {

                LoadResult.Page(
                    data = body.result,
                    prevKey = if(page == 0) null else page -1,
                    nextKey = if(page == body.totalPage) null else page +1
                )
            } else {
                LoadResult.Page(
                    data = body.result,
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            Log.d(TAG, "load: ${e.message}")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Story>): Int? {
        TODO("Not yet implemented")
    }
}