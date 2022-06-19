package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.util.hideKeyboard
import ru.netology.nmedia.viewMoel.PostViewModel
import android.widget.Toast
import ru.netology.nmedia.util.focusAndShowKeyboard


class MainActivity : AppCompatActivity(R.layout.post) {

    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        val adapter = PostsAdapter(viewModel)
        binding.postsRecyclerView.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.saveButton.setOnClickListener {
            with(binding.contentEditText) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Text can't be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                val content = text.toString()
                viewModel.onSaveButtonClicked(content)
                clearFocus()
                hideKeyboard()

            }
        }
        binding.closeEditButton.setOnClickListener {
            with(binding.contentEditText) {
                viewModel.onCloseEditClicked()
                clearFocus()
                hideKeyboard()
            }
            binding.groupForEdit.visibility = View.GONE
        }

        viewModel.currentPost.observe(this) { currentPost ->
            with(binding.contentEditText) {
                val content = currentPost?.content
                setText(content)
                if (content != null) {
                    binding.editMessageTextContent.text = content
                    binding.groupForEdit.visibility = View.VISIBLE
                    focusAndShowKeyboard()
                } else {
                    binding.groupForEdit.visibility = View.GONE
                    clearFocus()
                    hideKeyboard()
                }

                }
            }

        }
    }







