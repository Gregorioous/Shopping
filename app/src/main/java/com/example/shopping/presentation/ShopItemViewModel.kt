package com.example.shopping.presentation

import androidx.lifecycle.ViewModel
import com.example.shopping.data.ShopListRepositoryImpl
import com.example.shopping.domain.*

class ShopItemViewModel() : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val changesShopItemUseCase = ChangesShopItemUseCase(repository)


    val shopList = getShopListUseCase.GetShopList()

    fun getShopItem(shopItemId: Int){


    }

    fun addShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid =validateInput(name,count)
        if(fieldsValid) {
            val shopItem = ShopIten(name,count,true)
            addShopItemUseCase.AddShopItem(shopItem)
        }
    }
    fun changesShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid =validateInput(name,count)
        if(fieldsValid) {
            val shopItem = ShopIten(name,count,true)
            changesShopItemUseCase.ChangesShopItem(shopItem)
        }
    }

    private fun parseName(inputName: String?):String {
        return inputName?.trim() ?: ""
    }
    private fun parseCount(inputCount: String?):Int {
        return try {
        inputCount?.trim()?.toInt() ?: 0
        } catch (e:Exception){
            0
        }
    }

    private fun validateInput (name: String, count: Int):Boolean{
        var result = true
        if (name.isBlank()){
            result = false
        }
        if (count <= 0 ){
            result = false
        }
        return result

    }
}