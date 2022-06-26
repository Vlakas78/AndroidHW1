package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.dto.countView
import kotlin.math.floor


internal class PostsAdapter(

    private val interactionListener: PostInteractionListener
) : ListAdapter<Post, PostsAdapter.ViewHolder> (DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(
        private val binding: PostBinding,
        listener: PostInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var post: Post

        private val popupMenu by lazy {
            PopupMenu(itemView.context, binding.options).apply {
                inflate(R.menu.options_post)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.remove -> {
                            listener.onRemovedClicked(post)
                            true
                        }
                        R.id.edit -> {
                            listener.onEditClicked(post)
                            true
                        }
                        else -> false
                    }

                }
            }
        }


        init {
            binding.buttonLike.setOnClickListener { listener.onLikeClicked(post) }
            binding.buttonShare.setOnClickListener { listener.onShareClicked(post) }

        }

        fun bind(post: Post) {
            this.post = post
            with(binding) {
                authorName.text = post.author
                textPost.text = post.content
                date.text = post.published
                amountLike.text = countView(post.likes)
                buttonLike.setImageResource(if (post.likedByMe) R.drawable.ic_baseline_favorite_like_24 else R.drawable.ic_baseline_favorite_24)
                amountShare.text = countView(post.counterShare)
                options.setOnClickListener { popupMenu.show() }
            }
        }
    }
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

        private object DiffCallBack : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post) =
                oldItem == newItem
        }
    }





