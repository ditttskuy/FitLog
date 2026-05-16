package com.adit0080.assesment1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.adit0080.assesment1.model.Kalori
import kotlinx.coroutines.flow.Flow

@Dao
interface KaloriDao {
    @Insert
    suspend fun insert(kalori: Kalori)

    @Update
    suspend fun update(kalori: Kalori)

    @Query("SELECT * FROM kalori_table ORDER BY tanggal DESC")
     fun getKalori(): Flow<List<Kalori>>

    @Query("SELECT * FROM kalori_table WHERE id = :id")
    suspend fun getKaloriById(id: Long): Kalori?

    @Query("DELETE FROM kalori_table WHERE id = :id")
    suspend fun deleteKaloriById(id: Long)
}

