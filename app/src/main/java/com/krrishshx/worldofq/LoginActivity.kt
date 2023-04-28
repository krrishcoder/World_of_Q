package com.krrishshx.worldofq

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.krrishshx.worldofq.databinding.ActivityLoginBinding

import com.krrishshx.worldofq.view_models.Login_vm
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val vm:Login_vm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            val w :Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        if(vm.isAlreadySignInned()){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }else{
            Toast.makeText(this,"check for network",Toast.LENGTH_SHORT).show()
        }


        binding.btnStart.setOnClickListener {
            vm.Login(binding.etLoginId.text.toString(),binding.etLoginPass.text.toString())
            binding.loginSurface.visibility = View.INVISIBLE
            binding.progressSurface.visibility = View.VISIBLE

        }



        vm.toast_live_data.observe(this, Observer {
            Toast.makeText(this,"$it", Toast.LENGTH_SHORT).show()
            binding.loginSurface.visibility = View.VISIBLE
            binding.progressSurface.visibility = View.INVISIBLE
            if(it.equals(vm.FLAG_LOGIN)){
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        })

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }


    }

    override fun onStop() {
        super.onStop()

    }


}