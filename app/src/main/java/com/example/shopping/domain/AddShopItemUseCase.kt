package com.example.shopping.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun AddShopItem(shopItem:ShopIten) {
        shopListRepository.AddShopItem(shopItem )
    }
}