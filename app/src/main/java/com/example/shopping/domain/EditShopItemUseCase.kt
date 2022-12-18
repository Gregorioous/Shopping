package com.example.shopping.domain

class EditShopItemUseCase(private val shopListReposiitory: ShopListRepository) {

    suspend fun editShopItem(shopItem: ShopItem) {
        shopListReposiitory.editShopItem(shopItem)
    }
}