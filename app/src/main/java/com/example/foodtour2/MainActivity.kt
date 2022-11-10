package com.example.foodtour2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.foodtour2.bus.Event
import com.example.foodtour2.databinding.ActivityMainBinding
import com.example.foodtour2.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.mainViewModel=mainViewModel
            mainViewModel.isGo.observe(this){
                it.getContentIfNotHandled()?.let {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
    }
}
class EventWrapperObserver<T>(val callback: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(t: Event<T>?) {
        t?.getContentIfNotHandled()?.let {
            callback.invoke(it)
        }
    }
}