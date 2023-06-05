package com.example.homeworkplanner

import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.homeworkplanner.databinding.FragmentAllWorkBinding
import com.example.homeworkplanner.databinding.FragmentChooseBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AllWorkFragment : Fragment() {
    lateinit var dbRef: DatabaseReference
    private var _binding: FragmentAllWorkBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlanningViewModel by activityViewModels()
    lateinit var myMediaPlayer: MediaPlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbRef = Firebase.database.reference //in view model or every fragment
        _binding = FragmentAllWorkBinding.inflate(inflater, container, false)
        val rootView = binding.root
        myMediaPlayer = MediaPlayer.create(context, R.raw.sound)
        if (viewModel.numOfAssignments == 1) {
            binding.textView4.text = "You have ${viewModel.numOfAssignments} total assignment"
        } else
            binding.textView4.text = "You have ${viewModel.numOfAssignments} total assignments"
        setHasOptionsMenu(true)
        val assignments = viewModel.list
        viewModel.isCompleted.observe(viewLifecycleOwner) {
            myMediaPlayer.start()
        }
        val mAdapter = AssignmentAdapter(assignments)
        binding.recyclerView.adapter = mAdapter
        binding.returnButton4.setOnClickListener {
            rootView.findNavController().navigateUp()
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

    override fun onStop() {
        super.onStop()
        myMediaPlayer.release()
    }


}