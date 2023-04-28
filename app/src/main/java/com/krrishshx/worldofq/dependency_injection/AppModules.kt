package com.krrishshx.worldofq.dependency_injection

import android.content.Context
import com.krrishshx.worldofq.network_layer.api_question_service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://worldofq-ed0f4-default-rtdb.firebaseio.com/"

@InstallIn(SingletonComponent::class)
@Module
class AppModules {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context):BaseApplication{
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getQuestionAPI(retrofit:Retrofit):api_question_service{
        return retrofit.create(api_question_service::class.java)
    }


}