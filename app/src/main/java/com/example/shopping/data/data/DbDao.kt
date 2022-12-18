package com.example.shopping.data.data

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shopping.data.models.DbModel


@Dao
interface DbDao {

    @Query("SELECT * FROM data_table")
    fun getShopList(): LiveData<List<DbModel>>

    @Query("SELECT * FROM data_table")
    fun getShopListCursor(): Cursor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItemDbModel: DbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItemSync(shopItemDbModel: DbModel)

    @Query("DELETE FROM data_table WHERE id=:shopItemId")
    suspend fun deleteShopItem(shopItemId: Int)

    @Query("DELETE FROM data_table WHERE id=:shopItemId")
    fun deleteShopItemSycn(shopItemId: Int): Int

    @Query("SELECT * FROM data_table WHERE id=:shopItemId LIMIT 1")
    suspend fun getShopItem(shopItemId: Int): DbModel


}