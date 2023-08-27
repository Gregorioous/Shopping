package com.example.shopping.data

import com.example.shopping.domain.ShopIten

class ShopListMapper {

    fun mapEntityToDBModel(shopIten: ShopIten) = ShopItemDBModel(
        id = shopIten.id,
        name = shopIten.name,
        count = shopIten.count,
        enabled = shopIten.enabled
    )
    fun mapDBModelToEntity(shopItemDBModel: ShopItemDBModel) = ShopIten(
        id = shopItemDBModel.id,
        name = shopItemDBModel.name,
        count = shopItemDBModel.count,
        enabled = shopItemDBModel.enabled
    )
    fun mapListModelToListEntity(list: List<ShopItemDBModel>) = list.map {
                mapDBModelToEntity(it)
    }
}