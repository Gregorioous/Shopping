package com.example.shopping.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.shopping.domain.ShopIten
import com.example.shopping.data.ShopListRepositoryImpl
import com.example.shopping.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application):AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val changesShopItemUseCase = ChangesShopItemUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.IO)

    val shopList = getShopListUseCase.GetShopList()


    fun deleteShopItem(shopIten: ShopIten){
        scope.launch {
            deleteShopItemUseCase.DeleteShopItem(shopIten)
        }
    }
    fun changesShopItem(shopIten: ShopIten){
        scope.launch {
            val newItem = shopIten.copy(enabled = !shopIten.enabled)
            changesShopItemUseCase.ChangesShopItem(newItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}