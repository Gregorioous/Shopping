package com.example.shopping.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopping.domain.ShopIten
import com.example.shopping.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {
    private val shoplist = sortedSetOf<ShopIten>(Comparator<ShopIten> { o1, o2 -> o1.id.compareTo(o2.id) })
    private val shopListLD = MutableLiveData<List<ShopIten>>()
    private var autoIncrimentId = 0

init {
    for (i in 0 until 10){
        val item = ShopIten("Name: $i",i,true)
        AddShopItem(item)
    }
}

    override fun AddShopItem(shopItem: ShopIten) {
        if (shopItem.id == ShopIten.UNDEFINED_ID) {
            shopItem.id = autoIncrimentId++
        }
       shoplist.add(shopItem)
        updateList()
    }

    override fun ChangesShopItem(shopItem: ShopIten) {
      val oldElement = GetShopItem(shopItem.id)
        shoplist.remove(oldElement)
        AddShopItem(shopItem)
    }

    override fun DeleteShopItem(shopItem: ShopIten) {
       shoplist.remove(shopItem)
        updateList()
    }

    override fun GetShopItem(shopItemId: Int): ShopIten {
        return shoplist.find { it.id == shopItemId }!!
    }

    override fun GetShopList(): LiveData<List<ShopIten>> {
       return shopListLD
    }

    private fun updateList() {
        shopListLD.value = shoplist.toList()
    }
}