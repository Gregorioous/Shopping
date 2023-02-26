package com.example.shopping.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

     fun deleteShopItemList(shopItem: ShopItem) {

         shopListRepository.deleteShopItem(shopItem)
    }
}