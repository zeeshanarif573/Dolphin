package com.retail.dolphinpos.presentation.features.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.retail.dolphinpos.domain.model.home.Product
import com.retail.dolphinpos.presentation.databinding.ItemProductsBinding

class ProductAdapter(
    private val items: MutableList<Product>,
    private val onProductClick: ((Product) -> Unit)? = null
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.pdtName.text = product.name
            binding.pdtImage.setImageResource(product.image)

            binding.root.setOnClickListener { onProductClick?.invoke(product) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateProducts(newProducts: List<Product>) {
        items.clear()
        items.addAll(newProducts)
        notifyDataSetChanged()
    }
}
