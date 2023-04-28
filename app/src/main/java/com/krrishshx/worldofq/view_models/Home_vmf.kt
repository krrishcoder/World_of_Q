package com.krrishshx.worldofq.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krrishshx.worldofq.repo.Question_repo

class Home_vmf(private val repo:Question_repo) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Home_vm(repo) as T
    }
}