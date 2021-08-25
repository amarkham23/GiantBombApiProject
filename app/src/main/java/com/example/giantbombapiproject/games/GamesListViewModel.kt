package com.example.giantbombapiproject.games

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.giantbombapiproject.data.GiantBombRepository

class GamesListViewModel @ViewModelInject constructor(
    private val repository: GiantBombRepository
) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val games = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    fun searchGames(query: String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "video game"
    }
}