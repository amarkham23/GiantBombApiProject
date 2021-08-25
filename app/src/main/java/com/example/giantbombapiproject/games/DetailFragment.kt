package com.example.giantbombapiproject.games

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.giantbombapiproject.R
import com.example.giantbombapiproject.databinding.DetailFragmentBinding

class DetailFragment : Fragment(R.layout.detail_fragment) {
    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = DetailFragmentBinding.bind(view)

        binding.apply {
            val game = args.game

            Glide.with(this@DetailFragment)
                .load(game.image.medium_url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: com.bumptech.glide.load.DataSource?, isFirstResource: Boolean): Boolean {
                        progressBar.isVisible = false
                        tvDescription.isVisible = game.deck != null
                        return false
                    }
                })
                .into(imageView)

            tvDescription.text = game.deck
            (activity as AppCompatActivity).supportActionBar?.title = game.name

        }
    }
}