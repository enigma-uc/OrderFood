package com.example.orderfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.todkars.shimmer.ShimmerRecyclerView


class RecipeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view= inflater.inflate(R.layout.fragment_recipe, container, false)
        view.findViewById<ShimmerRecyclerView>(R.id.shimmer_rcv).showShimmer()
        return view
    }


}