package com.example.shopping.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun GetShopItem(shopItemId:Int) : ShopIten?{
       return shopListRepository.GetShopItem(shopItemId)
    }
}