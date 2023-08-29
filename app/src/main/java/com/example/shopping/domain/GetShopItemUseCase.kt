package com.example.shopping.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun GetShopItem(shopItemId:Int) : ShopIten?{
       return shopListRepository.GetShopItem(shopItemId)
    }
}