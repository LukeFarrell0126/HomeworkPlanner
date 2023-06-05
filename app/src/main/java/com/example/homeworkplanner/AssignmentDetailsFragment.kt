package com.example.homeworkplanner

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.homeworkplanner.databinding.FragmentAssignmentDetailsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class AssignmentDetailsFragment : Fragment() {
    private var _binding: FragmentAssignmentDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    lateinit var myMediaPlayer: MediaPlayer

    private val viewModel: PlanningViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbRef = Firebase.database.reference //in view model or every fragment
        _binding = FragmentAssignmentDetailsBinding.inflate(inflater, container, false)
        val rootView = binding.root
val args = AssignmentDetailsFragmentArgs.fromBundle(requireArguments())
        val index = args.indexArg
        myMediaPlayer = MediaPlayer.create(context, R.raw.sound)

        setHasOptionsMenu(true)
        binding.completeButton.setOnClickListener {
            viewModel.completeAssignment(index)
            viewModel.removeAssignment(index)
            rootView.findNavController().navigateUp()
        }
        binding.returnButton5.setOnClickListener {
            rootView.findNavController().navigateUp()
        }
        val current = viewModel.list.get(index) //bounds issue
        binding.detailTimeText.text=(current.time).toString() + " hours"
        binding.detailNameText.text = current.name
        binding.detailTypeText.text = current.type
        binding.detailDateText.text = current.date
        binding.detailDescText.text = current.desc
        binding.detailClassText.text = current.subject
        binding.detailPointText.text = current.points.toString() + "pts"

        return rootView
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
    fun play(){

    }
    override fun onStop() {
        super.onStop()
        myMediaPlayer.release()
    }

}