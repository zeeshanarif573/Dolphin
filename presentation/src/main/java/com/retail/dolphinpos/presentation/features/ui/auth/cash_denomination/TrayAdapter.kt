package com.retail.dolphinpos.presentation.features.ui.auth.cash_denomination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.ItemTrayBinding
import com.retail.dolphinpos.presentation.databinding.ItemTrayHeaderBinding

class TrayAdapter(
    private val items: List<Tray>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Tray.Header -> TYPE_HEADER
            is Tray.Item -> TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = ItemTrayHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                HeaderViewHolder(binding)
            }

            TYPE_ITEM -> {
                val binding = ItemTrayBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ItemViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (val item = items[position]) {
            is Tray.Header -> (holder as HeaderViewHolder).bind(item)
            is Tray.Item -> (holder as ItemViewHolder).bind(item)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        (recyclerView.layoutManager as? GridLayoutManager)?.let { layoutManager ->
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (items[position]) {
                        is Tray.Header -> layoutManager.spanCount
                        is Tray.Item -> 1
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class HeaderViewHolder(private val binding: ItemTrayHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: Tray.Header) {
            binding.headerLabel.text = header.title
        }
    }

    inner class ItemViewHolder(private val binding: ItemTrayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Tray.Item) {
            binding.countLabel.text = item.label
            binding.countValue.text =
                binding.root.context.getString(R.string.count_with_value, item.count)
        }
    }

}