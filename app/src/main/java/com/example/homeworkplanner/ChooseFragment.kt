package com.example.homeworkplanner

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.homeworkplanner.databinding.FragmentChooseBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ChooseFragment : Fragment() {
    lateinit var dbRef: DatabaseReference
    private var _binding: FragmentChooseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlanningViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbRef = Firebase.database.reference //in view model or every fragment
        _binding = FragmentChooseBinding.inflate(inflater, container, false)
        val rootView = binding.root
setHasOptionsMenu(true)
        binding.returnButton2.setOnClickListener {
            rootView.findNavController().navigateUp()
        }
        binding.essayImage.setOnClickListener{
            viewModel.workType = "Essay"
        }
        binding.projectImage.setOnClickListener{
            viewModel.workType = "Project"
        }
        binding.homeworkImage.setOnClickListener{
            viewModel.workType = "Homework"
        }
        binding.essayImage.setOnClickListener{
            viewModel.workType="Essay"
            val action =ChooseFragmentDirections.actionChooseFragmentToCreateFragment()
            rootView.findNavController().navigate(action)
        }
        binding.projectImage.setOnClickListener{
            viewModel.workType="Project"
            val action =ChooseFragmentDirections.actionChooseFragmentToCreateFragment()
            rootView.findNavController().navigate(action)
        }
        binding.homeworkImage.setOnClickListener{
            viewModel.workType="Homework"
            val action =ChooseFragmentDirections.actionChooseFragmentToCreateFragment()
            rootView.findNavController().navigate(action)
        }
        return rootView
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

}