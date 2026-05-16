package com.adit0080.assesment1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adit0080.assesment1.database.KaloriDao
import com.adit0080.assesment1.model.Kalori
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: KaloriDao) : ViewModel() {
    val data : StateFlow<List<Kalori>> =  dao.getKalori().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

}