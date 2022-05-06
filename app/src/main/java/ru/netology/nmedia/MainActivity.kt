package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.countView
import ru.netology.nmedia.viewMoel.PostViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            binding.render(post)
        }

        binding.buttonLike?.setOnClickListener {
            viewModel.onLikeClicked()
        }
        binding.buttonShare.setOnClickListener {
            viewModel.onShareClicked()
        }
    }

    private fun ActivityMainBinding.render(post: Post) {
        authorName.text = post.author
        textPost.text = post.content
        date.text = post.published
        amountLike.text = countView(post.likes)
        buttonLike?.setImageResource(getLikeIconResId(post.likedByMe))
        amountShare.text = countView(post.counterShare)

    }

    @DrawableRes
    private fun getLikeIconResId(liked: Boolean) =
        if (liked) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_like_24
}






