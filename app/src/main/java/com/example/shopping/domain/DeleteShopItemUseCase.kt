package com.example.shopping.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun deleteShopItemList(shopItem: ShopItem) {

        shopListRepository.deleteShopItemList(shopItem)
    }
}