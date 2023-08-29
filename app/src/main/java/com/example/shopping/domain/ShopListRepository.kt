package com.example.shopping.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    suspend fun AddShopItem(shopItem: ShopIten)

    suspend fun ChangesShopItem(shopItem: ShopIten)

    suspend fun DeleteShopItem(shopItem: ShopIten)

    suspend fun GetShopItem(shopItemId:Int) : ShopIten

    fun GetShopList() : LiveData<List<ShopIten>>
}