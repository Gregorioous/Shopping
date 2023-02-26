package com.example.shopping.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.R
import com.example.shopping.domain.ShopItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var Shoplist = listOf<ShopItem>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_disabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = Shoplist[position]
        val status = if (shopItem.enadled){
            "Active"
        }
        else {
            "inActive"
        }

        viewHolder.view.setOnLongClickListener {
            true
        }
         if(shopItem.enadled){
             viewHolder.tvName.text = "${shopItem.name} $status"
             viewHolder.tvCount.text = shopItem.count.toString()
             viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context,android.R.color.black))
         }
    }

    override fun getItemCount(): Int {
        return Shoplist.size
    }

    class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }
}