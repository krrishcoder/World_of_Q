package com.krrishshx.worldofq.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Main_vm_f()  : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Main_vm() as T
    }
}