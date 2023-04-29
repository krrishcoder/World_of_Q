package com.krrishshx.worldofq.view_models

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Login_vm @Inject constructor() : ViewModel() {


    val FLAG_LOGIN = "Login Done!!"


    private var toast_message = MutableLiveData<String>()
    val toast_live_data :LiveData<String> get() = toast_message

    fun get_toast(message:String){
        toast_message.postValue(message)
    }
    private  lateinit var mAuth : FirebaseAuth
    init {
        mAuth = FirebaseAuth.getInstance()   //will used to login
        Log.d("debug:","init view model")
    }


    fun Login(id:String,pass:String){

        if(id== null || id.equals("")){
            get_toast("No id")
            return
        }

        if(pass == null || pass.equals("")){
            get_toast("No password")
            return
        }

            mAuth.signInWithEmailAndPassword(id,pass).addOnCompleteListener {
                if(it.isSuccessful){
                    get_toast("Login Done!!")
                }else{
                    get_toast("error in Login")
                    Log.d("debug:","error $id , $pass --> ${it.exception}")
                }
            }

    }

    fun Logout(){
        try{
              mAuth.signOut()
            get_toast("logout")
        }catch (e:java.lang.Exception){
            get_toast("error in logout")
        }
    }

    fun isAlreadySignInned():Boolean{
        //will be used to check is user already signed in
        if(mAuth.currentUser !=null){
            return true
        }
            return false
    }


}