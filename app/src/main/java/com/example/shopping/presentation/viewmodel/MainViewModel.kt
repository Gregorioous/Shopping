package com.example.shopping.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopping.data.repositories.ShoppingRepositories
import com.example.shopping.domain.EditShopItemUseCase
import com.example.shopping.domain.GetShopItemUseCase
import com.example.shopping.domain.GetShopListUseCase
import com.example.shopping.domain.ShopItem

class MainViewModel:ViewModel() {

    private val repository = ShoppingRepositories

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

}