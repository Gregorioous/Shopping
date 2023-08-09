package com.example.shopping.domain

class DeleteShopItemUseCase (private val shopListRepository: ShopListRepository) {
    fun DeleteShopItem(shopItemdeleted:ShopIten) {
        shopListRepository.DeleteShopItem(shopItemdeleted)
    }
}