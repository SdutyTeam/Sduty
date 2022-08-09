package com.d108.sduty.model.api

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.d108.sduty.model.dto.Timeline
import java.lang.Exception

private const val STARTING_PAGE_INDEX = 0
private const val TAG ="TimelinePagingSource"
class TimelinePagingSource(private val storyApi: StoryApi, private val userSeq: Int): PagingSource<Int, Timeline>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Timeline> {
        return try{
//            val page = params.key ?: STARTING_PAGE_INDEX
//            Log.d(TAG, "load: $page")
//            val response = storyApi.getAllPagingTimeline(userSeq, page)
//            val post = response?.body()
//            Log.d(TAG, "load: ${post}")
//
//            LoadResult.Page(
//                data = response.body()!!,
//                prevKey = null,
//                nextKey = page + 1)


                        val pageId = params.key?: 1
            val response = storyApi.getAllPagingTimeline(userSeq, pageId)
            val myModelList = response.body()?: listOf()

            if(response.isSuccessful && myModelList.isNotEmpty()) {
                LoadResult.Page(
                    data = myModelList,
                    prevKey = null,
                    nextKey = pageId + 1
                )
            } else {
                LoadResult.Page(
                    data = myModelList,
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
//    override fun getRefreshKey(state: PagingState<Int, Timeline>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
}