package com.example.shopping.presentation

import androidx.lifecycle.ViewModel
import com.example.shopping.domain.ShopIten
import com.example.shopping.data.ShopListRepositoryImpl
import com.example.shopping.domain.*

class MainViewModel:ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val changesShopItemUseCase = ChangesShopItemUseCase(repository)


    val shopList = getShopListUseCase.GetShopList()


    fun deleteShopItem(shopIten: ShopIten){
        deleteShopItemUseCase.DeleteShopItem(shopIten)

    }
    fun changesShopItem(shopIten: ShopIten){
        val newItem = shopIten.copy(enabled = !shopIten.enabled)
        changesShopItemUseCase.ChangesShopItem(newItem)

    }
}