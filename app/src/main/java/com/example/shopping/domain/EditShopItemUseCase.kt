package com.example.shopping.domain

class EditShopItemUseCase(private val shopListReposiitory: ShopListRepository) {

     fun editShopItem(shopItem: ShopItem) {
        shopListReposiitory.editShopItem(shopItem)
    }
}