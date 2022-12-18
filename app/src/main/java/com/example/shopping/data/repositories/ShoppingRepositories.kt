package com.example.shopping.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.shopping.data.data.DbDao
import com.example.shopping.domain.ShopItem
import com.example.shopping.domain.ShopListRepository
import java.lang.RuntimeException

class ShoppingRepositories @Inject constructor(
    private val dbDao: DbDao,
    private val mapper: ShopListMapper
) : ShopListRepository {

    override suspend fun insertShopItem(shopItem: ShopItem) {
        dbDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        dbDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        dbDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = dbDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        dbDao.getShopList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }
}