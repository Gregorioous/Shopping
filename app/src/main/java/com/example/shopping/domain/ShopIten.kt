package com.example.shopping.domain

import androidx.room.Entity

@Entity
data class ShopIten(
        val name:String,
        val count:Int,
        val enabled:Boolean,
        var id:Int = UNDEFINED_ID,
)
{
        companion object{
                const val UNDEFINED_ID = -1
        }
}
