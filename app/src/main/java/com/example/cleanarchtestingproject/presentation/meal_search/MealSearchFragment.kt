package com.example.cleanarchtestingproject.presentation.meal_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.cleanarchtestingproject.databinding.FragmentMealSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MealSearchFragment : Fragment() {
    private lateinit var binding: FragmentMealSearchBinding

    private val mealSearchViewModel: MealSearchViewModel by viewModels()

    private val mealSearchAdapter = MealSearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchMeal.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mealSearchViewModel.searchMealList(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        binding.searchRecyclerview.apply {
            adapter = mealSearchAdapter

        }

        lifecycle.coroutineScope.launchWhenCreated {

            mealSearchViewModel.mealSearchList.collect {
                if (it.isLoading) {

                    binding.progresbar.visibility=View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.progresbar.visibility=View.GONE
                }
                it.data?.let {
                    binding.progresbar.visibility=View.GONE
                    mealSearchAdapter.setContentList(it.toMutableList())
                }

            }
        }
        mealSearchAdapter.itemClickListener {
            findNavController().navigate(MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(mailId = it.mealId))
        }
    }
}


