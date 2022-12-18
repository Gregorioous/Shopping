package com.example.shopping.domain

class GetShopItemUseCase(private val shopListReposiitory: ShopListRepository) {

    suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopListReposiitory.getShopItem(shopItemId)
    }
}