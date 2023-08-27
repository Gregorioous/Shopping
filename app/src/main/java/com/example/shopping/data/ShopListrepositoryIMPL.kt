package com.example.shopping.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopping.domain.ShopIten
import com.example.shopping.domain.ShopListRepository

class ShopListRepositoryImpl(application: Application): ShopListRepository {
    private val shopListDao = AppDataBase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override fun AddShopItem(shopItem: ShopIten) {
        shopListDao.addShopItem(mapper.mapEntityToDBModel(shopItem))
    }

    override fun ChangesShopItem(shopItem: ShopIten) {
        shopListDao.addShopItem(mapper.mapEntityToDBModel(shopItem))
    }

    override fun DeleteShopItem(shopItem: ShopIten) {
       shopListDao.deleteShopItem(shopItem.id)
    }

    override fun GetShopItem(shopItemId: Int): ShopIten {
       val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDBModelToEntity(dbModel)
    }

    override fun GetShopList(): LiveData<List<ShopIten>> = MediatorLiveData<List<ShopIten>>().apply {
        addSource(shopListDao.getShopList()){
           value = mapper.mapListModelToListEntity(it)
        }
    }
}