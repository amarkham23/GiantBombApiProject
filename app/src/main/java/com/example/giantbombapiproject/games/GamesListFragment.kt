package com.example.giantbombapiproject.games

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.giantbombapiproject.R
import com.example.giantbombapiproject.data.GameObject
import com.example.giantbombapiproject.databinding.GameListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GamesListFragment : Fragment(R.layout.game_list_fragment), GamesListAdapter.OnItemClickListener {

    private val viewModel by viewModels<GamesListViewModel>()

    private var _binding: GameListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Game Search"


        _binding = GameListFragmentBinding.bind(view)

        val adapter = GamesListAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = GamesListLoadStateAdapter { adapter.retry() },
                footer = GamesListLoadStateAdapter { adapter.retry() },
            )
            retryBtn.setOnClickListener {
                adapter.retry()
            }
        }

        viewModel.games.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                retryBtn.isVisible = loadState.source.refresh is LoadState.Error
                tvErrorMessage.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1) {
                    recyclerView.isVisible = false
                    tvNoResults.isVisible = true
                } else {
                    tvNoResults.isVisible = false
                }
            }
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                retryBtn.isVisible = loadState.source.refresh is LoadState.Error
                tvErrorMessage.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached && adapter.itemCount < 1) {
                    recyclerView.isVisible = false
                    tvNoResults.isVisible = true
                } else {
                    tvNoResults.isVisible = false
                }

            }

        }
        setHasOptionsMenu(true)
    }

    override fun onItemClick(game: GameObject) {
        val action = GamesListFragmentDirections.actionGamesListFragmentToDetailFragment(game)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.search_bar, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {
                    binding.recyclerView.scrollToPosition(0)
                    viewModel.searchGames(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}