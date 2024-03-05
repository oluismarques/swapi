package com.swapi.tmdb.domain.base

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.swap.util.TMDBException
import com.swapi.tmdb.domain.MovieItem

private const val STARTING_PAGE_INDEX = 1

abstract class BasePagingSource<T : MovieItem>() : PagingSource<Int, T>() {

    protected abstract suspend fun fetchItems(page: Int): List<T>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = fetchItems(page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(TMDBException("Some error occurred"))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}