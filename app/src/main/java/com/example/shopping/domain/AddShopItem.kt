package com.example.shopping.domain

class AddShopItem(private val shopListReposiitory: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem) {

        shopListReposiitory.addShopItem(shopItem)
    }
}