package com.motus.cricketverse


import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.motus.cricketverse.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Dummy user data
        binding.userName.text = "Gursimran Singh"
        binding.userLocation.text = "Montreal, Canada"
        binding.userEmail.text = "simran@example.com"

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        view.startAnimation(animation)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}