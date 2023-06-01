package com.example.homeworkplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.homeworkplanner.databinding.FragmentAboutBinding
import com.example.homeworkplanner.databinding.FragmentMainBinding

class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.returnButton6.setOnClickListener {
            rootView.findNavController().navigateUp()
        }
        return rootView
    }


}