package com.example.shopping.domain

class AddShopItem(private val shopListReposiitory: ShopListRepository) {

    suspend fun addShopItem(shopItem: ShopItem) {

        shopListReposiitory.addShopItem(shopItem)
    }
}