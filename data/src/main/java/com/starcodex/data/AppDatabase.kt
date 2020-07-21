package com.starcodex.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.starcodex.data.stepform.source.dao.UserInfoDao
import com.starcodex.data.stepform.source.entity.UserInfoEntity

@Database(entities = arrayOf(
    UserInfoEntity::class
), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
}