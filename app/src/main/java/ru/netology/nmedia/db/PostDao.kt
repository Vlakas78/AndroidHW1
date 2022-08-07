package ru.netology.nmedia.db

import ru.netology.nmedia.dto.Post

interface PostDao {
   fun getAll(): List<Post>
   fun save(post: Post): Post
   fun likedById(id: Long)
   fun removeById(id: Long)
   fun share(id: Long)
    }
