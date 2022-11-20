package com.example.cleanarchtestingproject.presentation.meal_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cleanarchtestingproject.databinding.FragmentMealDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMealDetailsBinding
    private val args:MealDetailsFragmentArgs by navArgs()
    private val mealDetailsViewModel:MealDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args.mailId?.let {
            mealDetailsViewModel.getMealDetails(it)
        }
        lifecycle.coroutineScope.launchWhenCreated {
            mealDetailsViewModel.mealDetails.collect{
                if (it.isLoading) {
                    //binding.progresbar.visibility=View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    Toast.makeText(activity, it.error, Toast.LENGTH_SHORT).show()
                   // binding.progresbar.visibility=View.GONE
                }
                it.data?.let {
                    //binding.progresbar.visibility=View.GONE
                    binding.mealDetails=it
                }
            }
        }

        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}