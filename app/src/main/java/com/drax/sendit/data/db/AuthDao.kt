package com.drax.sendit.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AuthDao {
    @Query("DELETE FROM registry WHERE isUserData = 1")
    fun clearUserData()
    @Query("DELETE FROM connection")
    fun clearDeviceData()
    @Query("DELETE FROM `transaction`")
    fun clearHistoryData()
}