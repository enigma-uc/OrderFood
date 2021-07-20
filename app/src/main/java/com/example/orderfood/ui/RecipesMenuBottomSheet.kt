package com.example.orderfood.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.orderfood.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RecipesMenuBottomSheet: BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipes_menu_bottomsheet, container, false)
        return view
    }
}
