package com.example.shopping.domain

class GetShopItemUseCase(private val shopListReposiitory: ShopListRepository) {

     fun getShopItem(shopItemId: Int): ShopItem {
        return shopListReposiitory.getShopItem(shopItemId)
    }
}