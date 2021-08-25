package com.example.giantbombapiproject.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.giantbombapiproject.databinding.GamesListLoadingFooterBinding

class GamesListLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<GamesListLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = GamesListLoadingFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: GamesListLoadingFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryBtn.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                retryBtn.isVisible = loadState !is LoadState.Loading
                retryBtn.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}