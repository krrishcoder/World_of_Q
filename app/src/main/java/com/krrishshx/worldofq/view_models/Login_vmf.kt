package com.krrishshx.worldofq.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Login_vmf() :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Login_vm() as T
    }
}