package com.example.shopping.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.shopping.domain.ShopIten
import com.example.shopping.domain.ShopListRepository

class ShopListRepositoryImpl(application: Application): ShopListRepository {
    private val shopListDao = AppDataBase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override suspend fun AddShopItem(shopItem: ShopIten) {
        shopListDao.addShopItem(mapper.mapEntityToDBModel(shopItem))
    }

    override suspend fun ChangesShopItem(shopItem: ShopIten) {
        shopListDao.addShopItem(mapper.mapEntityToDBModel(shopItem))
    }

    override suspend fun DeleteShopItem(shopItem: ShopIten) {
       shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun GetShopItem(shopItemId: Int): ShopIten {
       val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDBModelToEntity(dbModel)
    }

    override fun GetShopList(): LiveData<List<ShopIten>> = Transformations.map(
        shopListDao.getShopList()
    ) {
        mapper.mapListModelToListEntity(it)
    }
}