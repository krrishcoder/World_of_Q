package com.krrishshx.worldofq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.FirebaseDatabase
import com.krrishshx.worldofq.databinding.ActivityTestingBinding

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Testing : AppCompatActivity() {

    lateinit var binding: ActivityTestingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding  = DataBindingUtil.setContentView(this,R.layout.activity_testing)






    }
}