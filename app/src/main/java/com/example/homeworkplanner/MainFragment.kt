package com.example.homeworkplanner

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.homeworkplanner.databinding.FragmentMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    private val viewModel: PlanningViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbRef = Firebase.database.reference //in view model or every fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root
        var hours = (viewModel.totalTime() / 1).toInt()
        var minutes = ((viewModel.totalTime() % 1) * 60).toInt()
        setHasOptionsMenu(true)

        if (viewModel.numOfAssignments == 0 || (minutes == 0 && hours == 0)) {
            binding.overviewText.text = "You have no ongoing assignments"
            binding.timeText.text = "You have about no work to complete"
        } else if (viewModel.numOfAssignments == 1) {
            binding.overviewText.text = "You have ${viewModel.numOfAssignments} assignment due"
        } else {
            binding.overviewText.text = "You have ${viewModel.numOfAssignments} assignments due"
        }
        if (minutes > 0.0) {
            if (minutes == 1)
                binding.timeText.text = "You have about ${minutes} minute of work to complete"
            else
                binding.timeText.text = "You have about ${minutes} minutes of work to complete"
        }
        if (hours > 0.0) {
            if (hours > 1.0 && minutes > 1.0)
                binding.timeText.text =
                    "You have about ${hours} hours and ${minutes} minutes of work to complete"
            else if (hours == 1) {
                binding.timeText.text = "You have about ${hours} hour and"
                if (minutes == 1)
                    binding.timeText.text =
                        binding.timeText.text.toString() + " ${minutes} minute of work to complete"
                else if(minutes == 0)
                    binding.timeText.text =
                        "You have about ${hours} hour of work to complete"
                else
                    binding.timeText.text =
                        binding.timeText.text.toString() + " ${minutes} minutes of work to complete"
            }
            else
                binding.timeText.text = "You have about ${hours} hours of work to complete"
        }
        binding.beginPlanningButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToActionFragment()
            rootView.findNavController().navigate(action)
        }
        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}