package ru.netology.nmedia.viewMoel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.data.impl.InMemoryPostRepositiry
import ru.netology.nmedia.dto.Post

class PostViewModel : ViewModel() {

    private val repository: PostRepository = InMemoryPostRepositiry()

    val data by repository :: data

    fun onLikeClicked(post : Post) = repository.like(post.id)
    fun onShareClicked(post: Post) = repository.share(post.id)

}