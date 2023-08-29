package com.example.shopping.domain

class DeleteShopItemUseCase (private val shopListRepository: ShopListRepository) {

   suspend fun DeleteShopItem(shopItemdeleted: ShopIten) {
        shopListRepository.DeleteShopItem(shopItemdeleted)
    }
}