package com.example.homeworkplanner

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.homeworkplanner.databinding.FragmentActionBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ActionFragment : Fragment() {
    lateinit var dbRef : DatabaseReference
    private var _binding: FragmentActionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlanningViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbRef = Firebase.database.reference //in view model or every fragment

        _binding = FragmentActionBinding.inflate(inflater, container, false)
        val rootView = binding.root

        setHasOptionsMenu(true)
        binding.addButton.setOnClickListener{
            val action = ActionFragmentDirections.actionActionFragmentToChooseFragment()
            rootView.findNavController().navigate(action)
        }
        binding.viewButton.setOnClickListener {
            if(viewModel.numOfAssignments>0) {
                val action = ActionFragmentDirections.actionActionFragmentToAllWorkFragment()
                rootView.findNavController().navigate(action)
            }
            else {
                Snackbar.make(
                    binding.headingText,
                    "You must create an assignment before viewing this screen",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        binding.returnButton.setOnClickListener {
            val action = ActionFragmentDirections.actionActionFragmentToMainFragment()
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