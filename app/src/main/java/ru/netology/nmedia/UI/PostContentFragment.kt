package ru.netology.nmedia.UI

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.nmedia.databinding.PostContentFragmentBinding
import ru.netology.nmedia.util.focusAndShowKeyboard

class PostContentFragment : Fragment() {

    private val args by navArgs<PostContentFragmentArgs>()

      override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = PostContentFragmentBinding.inflate(layoutInflater, container, false).also { binding ->
          binding.edit.setText(args.initialContent)
          binding.edit.focusAndShowKeyboard()
          binding.ok.setOnClickListener {
          onOkButtonClicked(binding)
          }
    }.root



private fun onOkButtonClicked(binding:PostContentFragmentBinding) {
        val intent = Intent()
        val text = binding.edit.text
        if (!text.isNullOrBlank()) {
            val resultBundle = Bundle(1)
            resultBundle.putString(RESULT_KEY, text.toString())

            if (args.fromFragment == REQUEST_KEY) {
                setFragmentResult(
                    REQUEST_KEY,
                    resultBundle
                )
            } else if (args.fromFragment == REQUEST_CURRENT_POST_KEY) {
                setFragmentResult(
                    REQUEST_CURRENT_POST_KEY,
                    resultBundle
                )
            }
        }
        findNavController().popBackStack()
    }



     companion object {
         const val REQUEST_KEY = "requestKey"
         const val RESULT_KEY = "postNewContent"
         const val RESULT_KEY_EDIT = "postEditContent"
         const val REQUEST_CURRENT_POST_KEY = "requestForCurrentPostFragmentKey"
    }

}
