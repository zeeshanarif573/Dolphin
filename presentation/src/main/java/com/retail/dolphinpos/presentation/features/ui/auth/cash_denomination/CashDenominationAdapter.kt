package com.retail.dolphinpos.presentation.features.ui.auth.cash_denomination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.retail.dolphinpos.domain.model.auth.cash_denomination.Denomination
import com.retail.dolphinpos.domain.model.auth.cash_denomination.DenominationType
import com.retail.dolphinpos.presentation.R
import com.retail.dolphinpos.presentation.databinding.ItemTrayBinding
import com.retail.dolphinpos.presentation.databinding.ItemTrayHeaderBinding

class CashDenominationAdapter(
    private val onDenominationClick: (Denomination) -> Unit
) : ListAdapter<CashDenominationItem, RecyclerView.ViewHolder>(DenominationDiffCallback()) {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    private var selectedDenomination: Denomination? = null

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CashDenominationItem.Header -> TYPE_HEADER
            is CashDenominationItem.DenominationItem -> TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
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
                ItemViewHolder(binding, onDenominationClick)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is CashDenominationItem.Header -> (holder as HeaderViewHolder).bind(item)
            is CashDenominationItem.DenominationItem -> (holder as ItemViewHolder).bind(item)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        (recyclerView.layoutManager as? GridLayoutManager)?.let { layoutManager ->
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (getItem(position)) {
                        is CashDenominationItem.Header -> layoutManager.spanCount
                        is CashDenominationItem.DenominationItem -> 1
                    }
                }
            }
        }
    }

    fun updateDenominations(denominations: List<Denomination>) {
        val items = mutableListOf<CashDenominationItem>()
        
        // Add cash denominations
        val cashDenominations = denominations.filter { it.type == DenominationType.CASH }
        if (cashDenominations.isNotEmpty()) {
            items.add(CashDenominationItem.Header("Cash"))
            items.addAll(cashDenominations.map { CashDenominationItem.DenominationItem(it) })
        }
        
        // Add coin denominations
        val coinDenominations = denominations.filter { it.type == DenominationType.COIN }
        if (coinDenominations.isNotEmpty()) {
            items.add(CashDenominationItem.Header("Coins"))
            items.addAll(coinDenominations.map { CashDenominationItem.DenominationItem(it) })
        }
        
        submitList(items)
    }

    fun updateSelectedDenomination(denomination: Denomination?) {
        selectedDenomination = denomination
        notifyDataSetChanged()
    }

    inner class HeaderViewHolder(private val binding: ItemTrayHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: CashDenominationItem.Header) {
            binding.headerLabel.text = header.title
        }
    }

    inner class ItemViewHolder(
        private val binding: ItemTrayBinding,
        private val onDenominationClick: (Denomination) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: CashDenominationItem.DenominationItem) {
            val denomination = item.denomination
            val isSelected = selectedDenomination?.value == denomination.value
            
            binding.countLabel.text = denomination.label
            binding.countValue.text = binding.root.context.getString(
                R.string.count_with_value, 
                denomination.count
            )

            updateSelectionState(isSelected)

            binding.root.setOnClickListener {
                onDenominationClick(denomination)
            }
        }
        
        private fun updateSelectionState(isSelected: Boolean) {
            if (isSelected) {
                binding.root.setBackgroundResource(R.drawable.background_cash_counter_tray_selected)
                binding.countLabel.setTextColor(binding.root.context.getColor(R.color.white))
                binding.countValue.setTextColor(binding.root.context.getColor(R.color.white))
            } else {
                binding.root.setBackgroundResource(R.drawable.background_cash_counter_tray)
                binding.countLabel.setTextColor(binding.root.context.getColor(R.color.colorTextLightMode))
                binding.countValue.setTextColor(binding.root.context.getColor(R.color.colorHint))
            }
        }
    }
}

sealed class CashDenominationItem {
    data class Header(val title: String) : CashDenominationItem()
    data class DenominationItem(val denomination: Denomination) : CashDenominationItem()
}

class DenominationDiffCallback : DiffUtil.ItemCallback<CashDenominationItem>() {
    override fun areItemsTheSame(oldItem: CashDenominationItem, newItem: CashDenominationItem): Boolean {
        return when {
            oldItem is CashDenominationItem.Header && newItem is CashDenominationItem.Header -> 
                oldItem.title == newItem.title
            oldItem is CashDenominationItem.DenominationItem && newItem is CashDenominationItem.DenominationItem -> 
                oldItem.denomination.value == newItem.denomination.value
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: CashDenominationItem, newItem: CashDenominationItem): Boolean {
        return oldItem == newItem
    }
}
