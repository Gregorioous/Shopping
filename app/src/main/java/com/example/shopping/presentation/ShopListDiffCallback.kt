package com.example.shopping.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shopping.domain.ShopIten

class ShopListDiffCallback (
    private val oldList : List<ShopIten>,
    private val newList : List<ShopIten>
        ) : DiffUtil.Callback(){

    override fun getOldListSize(): Int {
       return oldList.size
    }

    override fun getNewListSize(): Int {
      return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }


}