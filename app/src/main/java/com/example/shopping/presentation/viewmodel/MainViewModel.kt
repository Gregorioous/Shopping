package com.example.shopping.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopping.data.repositories.ShoppingRepositories
import com.example.shopping.domain.*

class MainViewModel:ViewModel() {

    private val repository = ShoppingRepositories

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()



    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItemList(shopItem)
    }


    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enadled = !shopItem.enadled)
        editShopItemUseCase.editShopItem(newItem)
    }

}