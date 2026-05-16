package com.adit0080.assesment1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adit0080.assesment1.database.KaloriDao
import com.adit0080.assesment1.model.Kalori
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DetailViewModel(private val dao: KaloriDao) : ViewModel()  {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(nama: String,tinggi: Double, berat: Double, usia: Int,gender: String,tingkatAktivitas: String,hasilKalori: Int){
        val kalori = Kalori(
            tanggal =formatter.format(Date()),
            nama = nama,
            tinggi = tinggi,
            berat = berat,
            usia = usia,
            gender = gender,
            tingkatAktivitas = tingkatAktivitas,
            hasilKalori = hasilKalori
        )
        viewModelScope.launch(Dispatchers.IO){
            dao.insert(kalori)
        }
    }
    suspend fun getKalori(id: Long): Kalori? {
        return dao.getKaloriById(id)
    }
    fun update(nama : String,id: Long,tinggi: Double, berat: Double, usia: Int,gender: String,tingkatAktivitas: String,hasilKalori: Int){
        val kalori = Kalori(
            id = id,
            tanggal =formatter.format(Date()),
            nama = nama,
            tinggi = tinggi,
            berat = berat,
            usia = usia,
            gender = gender,
            tingkatAktivitas = tingkatAktivitas,
            hasilKalori = hasilKalori
        )
        viewModelScope.launch(Dispatchers.IO){
            dao.update(kalori)
        }
    }
    fun delete(id: Long){
        viewModelScope.launch(Dispatchers.IO){
            dao.deleteKaloriById(id)
        }
    }

    }




