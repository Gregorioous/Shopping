package com.example.shopping.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shopping.domain.ShopIten

@Entity(tableName = "shop_items")
 data class ShopItemDBModel (
    @PrimaryKey(autoGenerate = true)
    val id:Int ,
    val name:String,
    val count:Int,
    val enabled:Boolean
         ){
 }