package com.example.shopping.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun GetShopList() : LiveData<List<ShopIten>> {
       return shopListRepository.GetShopList()
    }
}