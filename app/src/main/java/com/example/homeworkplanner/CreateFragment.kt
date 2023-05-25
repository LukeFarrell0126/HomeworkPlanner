package com.example.homeworkplanner

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.homeworkplanner.databinding.FragmentCreateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CreateFragment : Fragment() {

    private var _binding: FragmentCreateBinding? = null
    private val binding get() = _binding!!
    lateinit var dbRef: DatabaseReference
    var i = 1

    private val viewModel: PlanningViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbRef = Firebase.database.reference //in view model or every fragment
        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        val rootView = binding.root

        binding.returnButton3.setOnClickListener {
            rootView.findNavController().navigateUp()
        }

        if (binding.nameText.text.toString() == ("") || binding.dateText.text.toString() == ("") || binding.descText.text.toString() == ("")
            || binding.subjectText.text.toString() == ("") || binding.pointsText.text.toString() == ("")
            || binding.completeTimeText.text.toString() == ("")
        ) {
            binding.finish.setOnClickListener {
                Toast.makeText(
                    activity,
                    "You must complete ALL fields before creating an assignment",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            binding.finish.setOnClickListener {
                viewModel.addAssignment(
                    viewModel.workType,
                    binding.nameText.text.toString(),
                    binding.dateText.text.toString(),
                    binding.descText.text.toString(),
                    binding.subjectText.text.toString(),
                    binding.pointsText.text.toString().toInt(),
                    binding.completeTimeText.text.toString()
                )

                val action = CreateFragmentDirections.actionCreateFragmentToActionFragment()
                rootView.findNavController().navigate(action)
            }

        }
        binding.imageView2.setOnClickListener {
            if (i == 1) {
                binding.datePicker.setVisibility(View.VISIBLE)
                binding.finish.setVisibility(View.INVISIBLE)
                i++
            }
            else {
                binding.datePicker.setVisibility(View.INVISIBLE)
                binding.finish.setVisibility(View.VISIBLE)
                i--
            }
        }

        return rootView
    }

}