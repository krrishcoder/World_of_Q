package com.krrishshx.worldofq.dependency_injection

import android.app.Application
import android.util.Log
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        configureAmplify()

    }
    private fun configureAmplify(){

        try{

            Amplify.addPlugin(AWSApiPlugin())
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.configure(applicationContext)

            Log.i("debug:","intialized data store")
        }catch (e:Exception){

            Log.e("debug:","error in amplify inti ${e.message}")
        }
    }
}