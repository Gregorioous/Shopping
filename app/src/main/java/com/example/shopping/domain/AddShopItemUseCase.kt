package com.example.shopping.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

   suspend fun AddShopItem(shopItem: ShopIten) {
        shopListRepository.AddShopItem(shopItem )
    }
}