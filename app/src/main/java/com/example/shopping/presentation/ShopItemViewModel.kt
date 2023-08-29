package com.example.shopping.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopping.domain.ShopIten
import com.example.shopping.data.ShopListRepositoryImpl
import com.example.shopping.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)
    private val scope = CoroutineScope(Dispatchers.IO)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val changesShopItemUseCase = ChangesShopItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName : LiveData<Boolean>
    get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount : LiveData<Boolean>
        get() = _errorInputCount

    val shopList = getShopListUseCase.GetShopList()

    private val _shopItem = MutableLiveData<ShopIten>()
    val shopItem: LiveData<ShopIten>
        get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getShopItem(shopItemId: Int) {
        scope.launch {
            val item = getShopItemUseCase.GetShopItem(shopItemId)
            _shopItem.value = item
        }
    }

    fun addShopItem(inputName: String?, inputCount: String?){
        scope.launch {
            val name = parseName(inputName)
            val count = parseCount(inputCount)
            val fieldsValid =validateInput(name,count)
            if(fieldsValid) {
                val shopItem = ShopIten(name,count,true)
                addShopItemUseCase.AddShopItem(shopItem)
                finishWork()
            }
        }
    }
    fun changesShopItem(inputName: String?, inputCount: String?){
        scope.launch {
            val name = parseName(inputName)
            val count = parseCount(inputCount)
            val fieldsValid =validateInput(name,count)
            if(fieldsValid) {
                _shopItem.value?.let {
                    val item = it.copy(name = name,count =  count)
                    changesShopItemUseCase.ChangesShopItem(item)
                    finishWork()
                }
            }
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
            _errorInputName.value = true
            result = false
        }
        if (count <= 0 ){
            _errorInputCount.value = true
            result = false
        }
        return result

    }

     fun resetErrorInputName(){
        _errorInputName.value = false
    }

     fun resetErrorInputCount(){
        _errorInputCount.value = false
    }
    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}