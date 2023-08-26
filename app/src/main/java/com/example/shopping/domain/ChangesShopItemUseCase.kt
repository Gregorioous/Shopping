package com.example.shopping.domain

class ChangesShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun ChangesShopItem(shopItemchanged: ShopIten){
        shopListRepository.ChangesShopItem(shopItemchanged)
    }
}