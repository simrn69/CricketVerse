package com.motus.cricketverse.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.motus.cricketverse.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    data class QuizQuestion(
        val question: String,
        val options: List<String>,
        val answer: Int
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val json = requireContext().assets.open("sample_quiz.json").bufferedReader().use { it.readText() }
        val quiz = Gson().fromJson(json, QuizQuestion::class.java)
        binding.questionText.text = quiz.question
        quiz.options.forEachIndexed { index, option ->
            val rb = RadioButton(requireContext()).apply {
                id = index
                text = option
            }
            binding.optionsGroup.addView(rb)
        }
        binding.root.alpha = 0f
        binding.root.animate().alpha(1f).setDuration(300).start()
        binding.submitButton.setOnClickListener {
            val selected = binding.optionsGroup.checkedRadioButtonId
            if (selected == -1) {
                binding.resultText.text = "Please select an option"
            } else if (selected == quiz.answer) {
                binding.resultText.text = "Correct!"
            } else {
                binding.resultText.text = "Incorrect!"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
