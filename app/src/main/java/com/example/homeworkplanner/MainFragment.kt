package com.example.homeworkplanner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.homeworkplanner.databinding.FragmentMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef : DatabaseReference
    private val viewModel: PlanningViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbRef = Firebase.database.reference //in view model or every fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root
        var hours = viewModel.totalTime()/1
        var minutes = (viewModel.totalTime()%1)*60
        if(viewModel.numOfAssignments== 0) {
            binding.overviewText.text="You have no ongoing assignments"
            binding.timeText.text="You have about no work to complete"
        }
        else if(viewModel.numOfAssignments==1){
            binding.overviewText.text="You have ${viewModel.numOfAssignments} assignment due"
        }
        else{
            binding.overviewText.text="You have ${viewModel.numOfAssignments} assignments due"
        }


        if(viewModel.totalTime()/1==0.0) {
            if(minutes ==1.0)
            binding.timeText.text="You have about ${minutes} minute of work to complete"
            else
                binding.timeText.text="You have about ${minutes} minutes of work to complete"
        }
        else if(viewModel.totalTime()/60>0.0){
            if(hours > 1.0 && minutes > 1.0)
            binding.timeText.text="You have about ${hours} hours and ${minutes} minutes of work to complete"
            else if(hours ==1.0) {
                binding.timeText.text = "You have about ${hours} hour and"
                if (minutes == 1.0)
                    binding.timeText.text = " ${minutes} minute of work to complete"
            }
        }
        binding.beginPlanningButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToActionFragment()
            rootView.findNavController().navigate(action)
        }
        return rootView
    }
}