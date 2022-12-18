package com.example.shopping.domain

class GetShopItemUseCase(private val shopListReposiitory: ShopListRepository) {

    fun getShopItem(shopItem: ShopItem) {
       return shopListReposiitory.getShopItem(shopItem)
    }
}