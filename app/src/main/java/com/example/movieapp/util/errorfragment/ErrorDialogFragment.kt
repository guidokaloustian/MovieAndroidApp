package com.example.movieapp.util.errorfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.DialogFragment
import com.example.movieapp.activity.MainActivity
import com.example.movieapp.activity.MovieActivity
import com.example.movieapp.databinding.ErrorFragmentBinding
import org.jetbrains.annotations.Nullable

class ErrorDialogFragment : DialogFragment() {

    private var errorTitle: String? = null
    private var errorMessage: String? = null
    private lateinit var binding: ErrorFragmentBinding

    companion object {
        private const val TITLE = "title"
        private const val MESSAGE = "message"
        fun newInstance(title: String?, message: String?): ErrorDialogFragment {
            val dialogFragment = ErrorDialogFragment()
            val args = Bundle()

            args.putString(TITLE, title)
            args.putString(MESSAGE, message)
            dialogFragment.arguments = args
            return dialogFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ErrorFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding.goBackButtonFragment.setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorTitle = arguments?.getString(TITLE)
        errorMessage = arguments?.getString(MESSAGE)
        binding.errorTitle.text = errorTitle
        binding.errorDescriptionText.text = errorMessage
    }
}
