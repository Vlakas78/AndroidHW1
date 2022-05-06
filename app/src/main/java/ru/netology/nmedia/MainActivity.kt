package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import kotlin.math.floor
import ru.netology.nmedia.dto.countView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "04 мая в 19:27",
            likedByMe = false
        )

        binding.render(post)
        binding.buttonLike?.setOnClickListener {
            post.likedByMe = !post.likedByMe
            if (post.likedByMe) post.likes++ else post.likes--
            binding.buttonLike.setImageResource(getLikeIconResId(post.likedByMe))
            binding.render(post)
        }
    }

    private fun ActivityMainBinding.render(post: Post) {
        authorName.text = post.author
        textPost.text = post.content
        date.text = post.published
        amountLike.text = countView(post.likes)
        buttonLike?.setImageResource(getLikeIconResId(post.likedByMe))

        buttonShare.setOnClickListener {
            post.counterShare++
            amountShare.text = countView(post.counterShare)
        }
    }

    @DrawableRes
    private fun getLikeIconResId(liked: Boolean) =
        if (liked) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_like_24
}






