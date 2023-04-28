package com.krrishshx.worldofq.network_layer

import com.krrishshx.worldofq.model_x.QResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

//https://worldofq-ed0f4-default-rtdb.firebaseio.com/Questions.json

const val BASE_URL = "https://worldofq-ed0f4-default-rtdb.firebaseio.com/"
interface api_question_service {

    @GET("Questions.json")
    suspend fun getQuesions() :Response<QResponse>

}