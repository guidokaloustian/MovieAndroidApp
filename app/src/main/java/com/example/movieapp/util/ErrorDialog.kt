package com.example.movieapp.util

import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ErrorDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.order_confirmation))
            .setPositiveButton(getString(R.string.ok)) { _,_ -> }
            .create()


}