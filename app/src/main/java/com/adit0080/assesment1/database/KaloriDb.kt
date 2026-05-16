package com.adit0080.assesment1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adit0080.assesment1.model.Kalori

@Database(entities = [Kalori::class], version = 1, exportSchema = false)
abstract class KaloriDb : RoomDatabase(){
    abstract val dao: KaloriDao

    companion object{
    @Volatile
    private var INSTANCE: KaloriDb? = null

        fun getInstance(context: Context): KaloriDb {

            synchronized(this) {
                var instance =INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KaloriDb::class.java,
                        "kalori.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}