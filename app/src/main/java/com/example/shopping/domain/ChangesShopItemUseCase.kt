package com.example.shopping.domain

class ChangesShopItemUseCase(private val shopListRepository: ShopListRepository) {

   suspend fun ChangesShopItem(shopItemchanged: ShopIten){
        shopListRepository.ChangesShopItem(shopItemchanged)
    }
}