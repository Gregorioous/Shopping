package com.example.shopping.domain

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
