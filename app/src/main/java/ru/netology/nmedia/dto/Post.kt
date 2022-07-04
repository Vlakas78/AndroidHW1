package ru.netology.nmedia.dto

import kotlinx.serialization.Serializable
import kotlin.math.floor

@Serializable
data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 999,
    var counterShare:Int = 5,
    var likedByMe: Boolean = false,
    val video: String? = null
)

fun countView(number: Int): String {
    return when {
        number in 0..999 -> number.toString()
        number < 10000 && number % 1000 < 100 -> "${(number / 1000)}K"
        number in 1100..9999 -> "${floor((number.toDouble() / 1000) * 10) / 10}K"
        number in 10000..999999 -> "${(number / 1000)}K"
        number % 1000000 < 100000 -> "${(number / 1000000)}M"
        number in 1000000..999999999 -> "${floor((number.toDouble() / 1000000) * 10) / 10}M"
        else -> "0"
    }
}