package ru.netology.nmedia.adapter


import ru.netology.nmedia.dto.Post

interface PostInteractionListener {
    fun onLikeClicked(post: Post)
    fun onShareClicked(post: Post)
    fun onRemovedClicked(post: Post)
    fun onEditClicked(post: Post)
}