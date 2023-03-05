package com.example.kanyerestquotes.ui

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.kanyerestquotes.MainViewModel
import com.example.kanyerestquotes.databinding.HomeFragmentBinding
import java.util.*


class HomeFragment: Fragment(), TextToSpeech.OnInitListener {

    private lateinit var binding: HomeFragmentBinding

    private lateinit var tts: TextToSpeech

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        binding = HomeFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        tts = TextToSpeech(requireContext(), this)
        tts.setLanguage(Locale.ROOT)
        tts.setPitch(0.2f)
        tts.setSpeechRate(1.0f)

        binding.refreshButton.setOnClickListener {
            viewModel.getQuote()
            viewModel.buttonAnimator(binding.refreshButton)
        }

        viewModel.quote.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.quotesTextHome.setText(it.quote)
                speakOut(it.quote)
            }
        })

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.getDefault()
        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    private fun speakOut(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        if (tts.isSpeaking) {
            tts.stop()
        }
        tts.shutdown()
        super.onDestroy()
    }

}