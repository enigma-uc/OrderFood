package com.example.orderfood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.orderfood.models.FoodRecipe
import com.example.orderfood.models.Result

class RecipesAdapter() : RecyclerView.Adapter<MyViewHolder>() {
    private var recipe = emptyList<Result>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipes_row_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = recipe[position].title
        holder.desc.text = recipe[position].summary
    }

    override fun getItemCount(): Int {
        return recipe.size
    }

    fun setData(newData: FoodRecipe) {
        val recipesDiffUtil = RecipesDiffUtil(recipe,newData.results)
        val diffUtil = DiffUtil.calculateDiff(recipesDiffUtil)
        recipe = newData.results
        diffUtil.dispatchUpdatesTo(this)
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var recipeImg: ImageView = itemView.findViewById(R.id.itemImg)
    var title: TextView = itemView.findViewById(R.id.itemTitle)
    var desc: TextView = itemView.findViewById(R.id.itemDesc)
}