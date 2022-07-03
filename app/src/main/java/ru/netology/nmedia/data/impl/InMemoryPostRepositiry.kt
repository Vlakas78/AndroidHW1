package ru.netology.nmedia.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.dto.Post

class InMemoryPostRepositiry : PostRepository {

    private var nextId = GENERATED_POSTS_AMOUNT.toLong()

    private val posts
        get() = checkNotNull(data.value) {
            "Data value should not be null"
        }

    override val data = MutableLiveData(
        List(GENERATED_POSTS_AMOUNT) { index ->
            Post(
                id = index + 1L,
                author = "Нетология",
                content = "Контент $index",
                published = "04 мая в 19:27",
                likedByMe = false,
                video = "https://www.youtube.com/watch?v=WhWc3b3KhnY"
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

    override fun delete(postId: Long) {
        data.value = posts.filterNot { it.id == postId }
    }

    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) insert(post) else update(post)

    }

    private fun update(post: Post) {
        data.value = posts.map {
            if (it.id == post.id) post else it
        }

    }

    private fun insert(post: Post) {
        data.value = listOf(
            post.copy(id = ++nextId)) + posts

    }

    private companion object{
        const val GENERATED_POSTS_AMOUNT = 1000
    }
}