package com.d108.sduty.model.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.d108.sduty.model.api.StoryApi
import com.d108.sduty.model.dto.Timeline
import java.lang.Exception

private const val TAG ="TimeLineDataSource"
class StoryDataSource(private val storyApi: StoryApi, private val userSeq: Int): PagingSource<Int, Timeline>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Timeline> {
        return try{

            val page = params.key?: 0
            val response = storyApi.getUserStoryList(userSeq, page)
            val body = response.body() as PagingResult<Timeline>
            Log.d(TAG, "load: ${page}")

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
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Timeline>): Int? {
        TODO("Not yet implemented")
    }
}