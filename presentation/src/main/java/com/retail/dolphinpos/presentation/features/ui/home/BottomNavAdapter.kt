package com.retail.dolphinpos.presentation.features.ui.home

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.ItemBottomNavBinding

class BottomNavAdapter(
    private var menus: List<String>,
    private val onMenuClick: (String) -> Unit
) : RecyclerView.Adapter<BottomNavAdapter.MenuViewHolder>() {

    private var selectedPosition = 0

    inner class MenuViewHolder(val binding: ItemBottomNavBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemBottomNavBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menus[position]
        holder.binding.navBarBtn.text = menu

        // Highlight selection
        if (selectedPosition == position) {
            holder.binding.navBarBtn.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(holder.itemView.context, R.color.primary)
                )
            holder.binding.navBarBtn.setTextColor(
                ResourcesCompat.getColor(holder.itemView.context.resources, R.color.white, null)
            )
        } else {
            holder.binding.navBarBtn.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(holder.itemView.context, R.color.nav_bar_button_clr)
            )
            holder.binding.navBarBtn.setTextColor(
                ResourcesCompat.getColor(holder.itemView.context.resources, R.color.black, null)
            )
        }

        // Handle clicks
        holder.binding.root.setOnClickListener {
            val adapterPos = holder.adapterPosition
            if (adapterPos != RecyclerView.NO_POSITION) {
                val prev = selectedPosition
                selectedPosition = adapterPos
                if (prev != RecyclerView.NO_POSITION) notifyItemChanged(prev)
                notifyItemChanged(selectedPosition)
                onMenuClick(menus[adapterPos])
            }
        }
    }

    override fun getItemCount(): Int = menus.size

    fun updateMenus(newMenus: List<String>) {
        menus = newMenus
        selectedPosition = 0 // reset to first item if you want
        notifyDataSetChanged()
    }
}
