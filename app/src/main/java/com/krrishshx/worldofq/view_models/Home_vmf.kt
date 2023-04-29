package com.krrishshx.worldofq.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Home_vmf() :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Home_vm() as T
    }
}