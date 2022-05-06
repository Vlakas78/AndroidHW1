package ru.netology.nmedia.viewMoel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.data.impl.InMemoryPostRepositiry

class PostViewModel : ViewModel() {

    private val repository: PostRepository = InMemoryPostRepositiry()

    val data by repository :: data

    fun onLikeClicked() = repository.like()
    fun onShareClicked() = repository.share()
}