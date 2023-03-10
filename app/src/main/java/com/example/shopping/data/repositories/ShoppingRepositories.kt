package com.example.shopping.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.shopping.data.data.DbDao
import com.example.shopping.data.models.DbModel
import com.example.shopping.domain.ShopItem
import com.example.shopping.domain.ShopListRepository
import java.lang.RuntimeException

object ShoppingRepositories  : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })

    private var autoIncrementId = 0

    init{
        for (i in 0 until 1000){
            val item = ShopItem("name $1", i, true)
            addShopItem(item)
        }
    }


override fun addShopItem(shopItem: ShopItem) {
    if (shopItem.id == ShopItem.UNDEFINED_ID) {
        shopItem.id = autoIncrementId++
    }
    shopList.add(shopItem)
    updateList()
}

override fun deleteShopItem(shopItem: ShopItem) {
    shopList.remove(shopItem)
    updateList()
}

override fun editShopItem(shopItem: ShopItem) {
    val oldElement = getShopItem(shopItem.id)
    shopList.remove(oldElement)
    addShopItem(shopItem)
}

override fun getShopItem(shopItemId: Int): ShopItem {
    return shopList.find {
        it.id == shopItemId
    } ?: throw RuntimeException("Element with id $shopItemId not found")
}

override fun getShopList(): LiveData<List<ShopItem>> {
    return shopListLD
}

private fun updateList() {
    shopListLD.value = shopList.toList()

}


}

