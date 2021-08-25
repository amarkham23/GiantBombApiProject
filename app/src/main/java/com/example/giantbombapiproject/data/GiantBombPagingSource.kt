package com.example.giantbombapiproject.data

import androidx.paging.PagingSource
import com.example.giantbombapiproject.api.GiantBombApi
import retrofit2.HttpException
import java.io.IOException

private const val GIANT_BOMB_STARTING_PAGE_INDEX = 1

class GiantBombPagingSource(
    private val giantBombApi: GiantBombApi,
    private val query: String
) : PagingSource<Int, GameObject>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameObject> {
        val position = params.key ?: GIANT_BOMB_STARTING_PAGE_INDEX

        return try {
            val response = giantBombApi.searchGames(query)
            val games = response.results

            LoadResult.Page(
                data = games,
                prevKey = if (position == GIANT_BOMB_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (games.isEmpty()) null else position + 1,
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}