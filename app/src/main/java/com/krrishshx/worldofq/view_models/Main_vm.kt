package com.krrishshx.worldofq.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.generated.model.Degree
import com.amplifyframework.datastore.generated.model.Streak
import com.amplifyframework.datastore.generated.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject


@HiltViewModel
class Main_vm @Inject constructor(): ViewModel() {

    private val  degree_list_mutable = MutableLiveData<List<String>>()
    val degree_list_live_data : LiveData<List<String>> get() =  degree_list_mutable

   private val mutable_data_to_toast = MutableLiveData<String>()
    val toast_live_data :LiveData<String> get() = mutable_data_to_toast

    var degree_selected = -1

    private lateinit var arr_degree : ArrayList<String>
   lateinit var arr_degree_with_id :ArrayList<String>

init{
    Log.d("debug:","main view model initialization .. ")
}



    fun create_user(user_name:String,roll_no:String,sem:String,degree:String,gmail_id:String){
        Log.d("debug:","called create a user in amplify")

        viewModelScope.launch {

            val item = User.builder()
                .username(user_name)
                .rollno(roll_no)
                .gmailId(gmail_id)
                .userDegreeId(degree)
                .semNo(sem.toInt())
                .build()

            Amplify.DataStore.save(item,{

                mutable_data_to_toast.postValue("Account created")
                Log.d("debug:","account created")

              val user_id =  it.item().id

                val streak = Streak.builder()
                    .streakCount(0)
                    .streakUserId(user_id)
                    .build()

                Amplify.DataStore.save(streak,{

                },{
                    Log.e("debug:","unable to create streak")
                })

            },{
                Log.e("debug:","unable to create account")
            })
        }

    }

    fun get_all_degree_avail(){
        Log.d("debug:","called all degree")
        viewModelScope.launch {
            arr_degree = ArrayList()
            arr_degree_with_id = ArrayList()

            Amplify.DataStore.query(Degree::class.java,{
                items->
               while (items.hasNext()){
                   val item = items.next()
                   arr_degree.add(item.dname)
                   arr_degree_with_id.add("${item.id}")
               }
                degree_list_mutable.postValue(arr_degree)

            },{

                Log.e("debug:","unable to get degree")
            })

        }
    }

    fun check_entered_data(user_name:String,roll_no:String,sem:String,gmail:String,pass:String):Boolean{

        Log.d("debug:","degree selected was ${degree_selected}")

        if(roll_no!!.equals("")){
            return false
        }
        if(user_name.equals("")){
            return false
        }


        if(sem!!.equals("")){
            return false
        }

        if(gmail.length <=4){
            return false
        }
        if(pass.length <= 4){
            return false
        }

        if(degree_selected <0){
            return false
        }

        return true
    }

}