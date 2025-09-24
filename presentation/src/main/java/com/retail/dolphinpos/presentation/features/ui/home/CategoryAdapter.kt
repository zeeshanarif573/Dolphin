package com.retail.dolphinpos.presentation.features.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.retail.dolphinpos.domain.model.home.ProductCategory
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.ItemCategoriesBinding

class CategoryAdapter(
    private val context: Context,
    private val items: MutableList<ProductCategory>,
    private val onCategoryClick: (ProductCategory) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = 0

    inner class CategoryViewHolder(val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: ProductCategory, isSelected: Boolean) {
            binding.category.text = category.name

            if (isSelected) {
                binding.category.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.primary
                    )
                )
                binding.category.setTextColor(
                    ResourcesCompat.getColor(
                        context.resources,
                        R.color.white,
                        null
                    )
                )
            } else {
                binding.category.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.white
                    )
                )
                binding.category.setTextColor(
                    ResourcesCompat.getColor(
                        context.resources,
                        R.color.black,
                        null
                    )
                )
            }

            binding.root.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val prev = selectedPosition
                    selectedPosition = pos
                    notifyItemChanged(prev)
                    notifyItemChanged(selectedPosition)
                    onCategoryClick(category)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = items[position]
        holder.bind(category, position == selectedPosition)
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newList: List<ProductCategory>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}
