package com.example.orderfood.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orderfood.*
import com.example.orderfood.Constants.Companion.API_KEY
import com.example.orderfood.di.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeFragment : Fragment() {
    private lateinit var shimmerRcv: ShimmerRecyclerView
    private lateinit var floatingButton: FloatingActionButton
    private lateinit var mView: View
    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy {RecipesAdapter()}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
        mView = view
        shimmerRcv = view.findViewById(R.id.shimmer_rcv)
        floatingButton = view.findViewById(R.id.menuIcon)
        shimmerRcv.showShimmer()
        floatingButton.setOnClickListener {
            RecipesMenuBottomSheet().show(childFragmentManager, "SASA")
        }

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        setupRecyclerView()
        requestApiData()
        return view
    }

    private fun requestApiData()
    {
        mainViewModel.getRecipes(applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner,{ response->
            when(response)
            {
                is NetworkResult.Success -> {
                    hideShimmer()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmer()
                }
            }
        })
    }

    private fun applyQueries():HashMap<String,String>
    {
        val queries: HashMap<String,String> = HashMap()
        queries["number"] = "50"
        queries["apiKey"] = API_KEY
        queries["type"] = "snack"
        queries["diet"] = "vegan"
        queries["addRecipeInformation"] = "true"
        queries["fillIngredients"]= "true"
        return queries
    }
    private fun showShimmer()
    {
        shimmerRcv.showShimmer()
    }
    private fun hideShimmer()
    {
        shimmerRcv.hideShimmer()
    }
    private fun setupRecyclerView()
    {
        shimmerRcv.adapter = mAdapter
        shimmerRcv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        showShimmer()
    }
}