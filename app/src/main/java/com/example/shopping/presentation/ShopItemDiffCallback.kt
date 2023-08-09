package com.example.shopping.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shopping.domain.ShopIten

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopIten>() {

    override fun areItemsTheSame(oldItem: ShopIten, newItem: ShopIten): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopIten, newItem: ShopIten): Boolean {
        return oldItem == newItem
    }


}