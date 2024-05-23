package com.swapi.tmdb.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import asDomainModel
import com.swapi.tmdb.data.network.service.MoviesService
import com.swapi.tmdb.domain.movie.MovieItem
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

internal class SearchMoviePagingSource (
    private val query: String,
    private val moviesService: MoviesService,
) : PagingSource<Int, MovieItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return  state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        return try {
            val currentPage = params.key ?: 1
            val response = moviesService.search(query = query, page = currentPage)

            val data = response.movieItemResponses.asDomainModel()
            val endOfPaginationReached = data.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            LoadResult.Page(
                data = data,
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
        catch (exception: IllegalArgumentException) {
            return LoadResult.Error(exception)
        }
    }
}
