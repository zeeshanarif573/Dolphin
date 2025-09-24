package com.retail.dolphinpos.presentation.features.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.retail.dolphinpos.domain.model.home.Cart
import com.retail.dolphinpos.presentation.databinding.ItemCartBinding

class CartAdapter(
    private val items: MutableList<Cart>,
    private val onItemClick: ((Cart) -> Unit)? = null
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cart: Cart, serialNo: Int) {
            binding.sNo.text = "$serialNo"
            binding.pdtName.text = cart.productName
            binding.price.text = "${cart.price}"
            binding.quantity.text = "${cart.quantity}"
            binding.pdtImage.setImageResource(cart.image)

            binding.root.setOnClickListener {
                onItemClick?.invoke(cart)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position], position + 1)
    }

    override fun getItemCount(): Int = items.size

    // Optional → Update data dynamically
    fun updateData(newList: List<Cart>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}
