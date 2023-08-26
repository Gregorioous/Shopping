package com.example.shopping.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun AddShopItem(shopItem: ShopIten)

    fun ChangesShopItem(shopItem: ShopIten)

    fun DeleteShopItem(shopItem: ShopIten)

    fun GetShopItem(shopItemId:Int) : ShopIten

    fun GetShopList() : LiveData<List<ShopIten>>
}