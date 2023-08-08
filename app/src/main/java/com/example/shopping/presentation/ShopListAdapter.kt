package com.example.shopping.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shopping.R
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
        val view =
            LayoutInflater.from(parent.context).
            inflate(R.layout.item_disabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)

        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
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