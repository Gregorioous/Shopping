package com.example.shopping.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.R
import com.example.shopping.databinding.ItemDisabledBinding
import com.example.shopping.databinding.ItemEnabledBinding
import com.example.shopping.domain.ShopIten

class ShopListAdapter : ListAdapter<ShopIten, ShopItemViewHolder>(ShopItemDiffCallback()) {


    var onShopItemLongClickListener:((ShopIten) -> Unit?)? = null
    var onShopItemClickListener: ((ShopIten) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when(viewType){
            VIEW_TYPE_DISABLED -> R.layout.item_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_enabled
            else -> throw RuntimeException("Unknown view Type: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false)
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding
       binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        when(binding){
            is ItemDisabledBinding -> {
                binding.shopItem = shopItem
            }
            is ItemEnabledBinding -> {
                binding.shopItem = shopItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled){
            VIEW_TYPE_ENABLED
        } else
        {
            VIEW_TYPE_DISABLED
        }
    }
    
    companion object {

        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 30
    }
}