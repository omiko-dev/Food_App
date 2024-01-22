package com.example.food_recept.presentation.screen.dialog

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.food_recept.databinding.FragmentActionDialogBinding


class ActionDialogFragment : DialogFragment() {
    private var _binding: FragmentActionDialogBinding? = null
    private val binding get() = _binding!!
    var youtubeLink = ""
    var googleLink = ""


    override fun onResume() {
        super.onResume()
        val window = dialog?.window
        window?.setGravity(Gravity.TOP or Gravity.END)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActionDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToYoutubeSource()
        goToGoogleSource()
    }

    private fun goToYoutubeSource(){
        binding.bYoutube.setOnClickListener {
            openUrl(youtubeLink)
        }
    }

    private fun goToGoogleSource(){
        binding.bGoogle.setOnClickListener {
            openUrl(googleLink)
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}