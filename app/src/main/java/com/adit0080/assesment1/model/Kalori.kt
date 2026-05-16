package com.adit0080.assesment1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kalori_table")
data class Kalori (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nama : String,
    val tinggi: Double,
    val berat: Double,
    val usia: Int,
    val gender: String,
    val tingkatAktivitas: String,
    val hasilKalori: Int,
    val tanggal: String
)