package com.example.foodtour2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodtour2.bus.Event
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel(application: Application):AndroidViewModel(application) {
    private val _isGo = MutableLiveData<Event<Boolean>>()
    val isGo : LiveData<Event<Boolean>> get() =_isGo
    fun onClickGo(){
        _isGo.value = Event(true)
    }
}