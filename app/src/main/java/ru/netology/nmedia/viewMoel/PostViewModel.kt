package ru.netology.nmedia.viewMoel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.adapter.PostInteractionListener
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.data.impl.InMemoryPostRepositiry
import ru.netology.nmedia.dto.Post

class PostViewModel : ViewModel(), PostInteractionListener {

    private val repository: PostRepository = InMemoryPostRepositiry()

    val data by repository :: data

    val currentPost = MutableLiveData<Post?>(null)

    fun onSaveButtonClicked(content : String) {
        if(content.isBlank()) return

        val newPost = currentPost.value?.copy(
            content = content
        ) ?: Post(
            id = PostRepository.NEW_POST_ID,
            author = "Me",
            content = content,
            published = "Today"
        )
        repository.save(newPost)
        currentPost.value = null
    }

    fun onCloseEditClicked() {currentPost.value = null
    }

    // region PostInteractionListener

    override fun onLikeClicked(post : Post) = repository.like(post.id)

    override fun onShareClicked(post: Post) = repository.share(post.id)

    override fun onRemovedClicked(post: Post)  = repository.delete(post.id)

    override fun onEditClicked(post: Post) {currentPost.value = post
    }

    // endregion PostInteractionListener
}