package ru.netology.nmedia.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.dto.Post

class InMemoryPostRepositiry : PostRepository {

    private val posts
        get() = checkNotNull(data.value) {
            "Data value should not be null"
        }

    override val data = MutableLiveData(
        List(10) { index ->
            Post(
                id = index + 1L,
                author = "Нетология",
                content = "Контент $index",
                published = "04 мая в 19:27",
                likedByMe = false
            )
        }
    )

    override fun like(postId: Long) {
        data.value = posts.map { post ->
            if (post.id != postId) post
            else {
                if (post.likedByMe) post.likes-- else post.likes++
                post.copy(likedByMe = !post.likedByMe, likes = post.likes)
            }

        }
    }

    override fun share(postId: Long) {
        data.value = posts.map { post ->
            if (post.id != postId) post
            else {
                post.copy(counterShare = post.counterShare + 1)
            }
        }

    }
}