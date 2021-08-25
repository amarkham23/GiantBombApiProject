package com.example.giantbombapiproject.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.giantbombapiproject.data.GameObject
import com.example.giantbombapiproject.databinding.GameItemBinding

class GamesListAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<GameObject, GamesListAdapter.GameViewHolder>(GAME_COMPARATOR) {

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentItem = getItem(position)


        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding =
            GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GameViewHolder(binding)
    }

    inner class GameViewHolder(private val binding: GameItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(game: GameObject) {
            binding.apply {
                tvGameTitle.text = game.name

                Glide.with(itemView)
                    .load(game.image.medium_url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)

            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(photo: GameObject)
    }

    companion object {
        private val GAME_COMPARATOR = object : DiffUtil.ItemCallback<GameObject>() {
            override fun areItemsTheSame(oldItem: GameObject, newItem: GameObject) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: GameObject, newItem: GameObject) =
                oldItem == newItem

        }
    }
}