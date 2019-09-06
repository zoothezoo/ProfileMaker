package jp.co.cyberagent.dojo2019.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//dbの生成
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DB_NAME = "User"
        fun init(context: Context) {
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build().also { instance = it }
        }

        private var instance: AppDatabase? = null
        fun getInstance(): AppDatabase {
                return instance!!
        }

    }
}