package com.d108.sduty_admin.model.paging

data class PagingResult<T>(var page: Int, var totalPage: Int, var result: List<T>) {
}