package com.example.shopping.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun GetShopList() : LiveData<List<ShopIten>> {
       return shopListRepository.GetShopList()
    }
}