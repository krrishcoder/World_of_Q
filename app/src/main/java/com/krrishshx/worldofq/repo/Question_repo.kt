package com.krrishshx.worldofq.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.krrishshx.worldofq.model_x.QResponse
import com.krrishshx.worldofq.network_layer.api_question_service
import javax.inject.Inject

class Question_repo @Inject constructor(val apiQuestionService: api_question_service) {

    private val Question_mutable = MutableLiveData<QResponse>()
    val Qlist_live_data : LiveData<QResponse>
    get() = Question_mutable

    suspend fun getQuestions(){

        try{
            val result = apiQuestionService.getQuesions()
            Log.d("debug:","called network")
            if(result != null && result?.body() != null){
                Question_mutable.postValue(result.body())
            }else{
                Log.d("debug:","body is empty")
            }
        }catch (e:java.lang.Exception){
            Log.d("debug::","repo error")
            throw Exception("network error")
        }
    }

}