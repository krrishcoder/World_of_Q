package com.krrishshx.worldofq.view_models

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.generated.model.*
import com.google.firebase.auth.FirebaseAuth
import com.krrishshx.worldofq.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class Home_vm @Inject constructor() :ViewModel(){


    /////////////setting image///////////////
private val mutable_img_src = MutableLiveData<String>()
    val live_profile_img : LiveData<String> get() = mutable_img_src


   fun get_profile_img(img:String){
       mutable_img_src.postValue(img)
   }

    ////////////////////////////////////////




     var mAuth : FirebaseAuth
    var orien_changed =0;
    var flag_finish_code =1

    ///////////////////////////////////////////////////////////////no way to cancel a test
    ///////////////////////////////////previous result and streak increment when test is started///////also update the streak count//////////////////////////////
    //save the option and question no. in array index

    lateinit var arr_user_response : ArrayList<Int>
    lateinit var answers_arr : ArrayList<Int>

    fun calculate_of_marks():Int{                      //problem with test3 LOG
        var total_mark =0
        Log.d("debug:"," test3  question size is -- > ${question_arr.size}")

        for(i in 0..(question_arr.size-1)){
            //if user has not responded than arr_user_resposne will give -1
            //if he has responded than match answer to responded
            try {
                if (arr_user_response[i] >= 0 && answers_arr[i] == arr_user_response[i]) {
                    total_mark++
                }
            }catch (e:Exception){
                Log.e("debug:","problem in calculation of marks")
            }
        }
        Log.d("debug:"," test3  answers responded    -- > ${arr_user_response.toString()}  score --> ${total_mark}")

        return total_mark
    }

//also get the previous result if there is any for the same topic so that attempt no. can be known




    var attempt_num =0
    fun get_attempt_num(topic_id_sel:String,current_user_id:String){


        viewModelScope.launch {
            Amplify.DataStore.query(
                Result::class.java,
                Where.matches(
                    Result.RESULT_USER_ID.eq(current_user_id).and(
                        Result.RESULT_SUBJECT_ID.eq(subject_chosen_id)
                            .and(Result.RESULT_TOPIC_ID.eq(curr_topic_id))
                    )
                ).sorted(Result.ATTEMPT_NO.descending()),
                { items ->
                    while (items.hasNext()) {
                        val item = items.next()
                        attempt_num = item.attemptNo
                      //  Log.d("debug:", "test2 --> attempt no got is ${attempt_num}")
                        break
                    }
                },
                {
                    Log.e("debug:", "no network")
                    get_toast("Unable To create result")
                })

        }
    }





    fun create_result(topic_id_sel:String,current_user_id:String, total_time_sec:Int){

    val total_score = calculate_of_marks()



    val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
  //  Log.d("debug:","test2 --> attempt no got while creating result ${attempt_num}")

        viewModelScope.launch {


            val item: Result = Result.builder()
                .resultSubjectId(subject_chosen_id)
                .resultTopicId(curr_topic_id)
                .resultUserId(current_user_id)
                .timeFinished(total_time_sec.toString())
                .date(date)
                .attendedQuestion(total_score)
                .totalMarks(question_arr.size)
                .attemptNo(attempt_num+1)
                .build()

            Amplify.DataStore.save(
                item,
                { success -> Log.i("Amplify", "Saved item: " + success.item().resultTopicId) },
                { error -> Log.e("Amplify", "Could not save item to DataStore", error) }
            )
        }
    }


    fun update_streak_count(){
                val prev_count = current_streak_item.streakCount
               val to_save = current_streak_item.copyOfBuilder().streakCount(prev_count+1).build()
                Amplify.DataStore.save(to_save,{
                    mutable_streak.postValue(prev_count+1)
                },{
                    Log.e("debug:","no network")
                    get_toast("Unable To get Streaks")

                })
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////getting streak///////////////////////////////////
    private val mutable_streak = MutableLiveData<Int>()
    val streak_live_data :LiveData<Int> get() = mutable_streak
    var current_user_streak_id =""
    lateinit var current_streak_item :Streak


    //note that this must be called after knowing the profile

    fun get_streak_count_from_server(current_user_id:String){
                    //pass the user to know the streak count

        Amplify.DataStore.query(Streak::class.java,Where.matches(Streak.STREAK_USER_ID.eq(current_user_id)),{
             items->
            while(items.hasNext()){
                val item = items.next()
                current_streak_item = item
                current_user_streak_id = item.id
                mutable_streak.postValue(item.streakCount)
            }

        },{
            Log.e("debug:","no network")
            get_toast("Unable To get Streaks")
        })

    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    init{

        mAuth = FirebaseAuth.getInstance()   //will used to login
    }


    private var toast_message = MutableLiveData<String>()
    val toast_live_data :LiveData<String> get() = toast_message

    fun get_toast(message:String){
        toast_message.postValue(message)
    }

    fun Logout(){
        try{
            mAuth.signOut()
            get_toast("logout")
        }catch (e:java.lang.Exception){
            get_toast("error in logout")
        }
    }

    //////////////////////////////////////profile frag///////////////////////////////////////////////


    private val mutable_profile_data = MutableLiveData<User>()
    val profile_live_data :LiveData<User> get() = mutable_profile_data

    private val mutable_degree_data =MutableLiveData<String>()
    val degree_live_data :LiveData<String> get() = mutable_degree_data



    private val mutable_subject_data =MutableLiveData<List<my_subject_model>>()
    val subject_live_data :LiveData<List<my_subject_model>> get() = mutable_subject_data


    private val mutable_question_data = MutableLiveData<List<my_question_model>>()
    val question_live_data : LiveData<List<my_question_model>> get() = mutable_question_data


    private val mutable_topic_data = MutableLiveData<List<my_topic_model>>()
    val topic_live_data : LiveData<List<my_topic_model>> get() = mutable_topic_data

     lateinit var subjects_arr  :ArrayList<my_subject_model>
     lateinit var topic_arr : ArrayList<my_topic_model>

     lateinit var question_arr :ArrayList<my_question_model>


    var degree_id_current =""
    var sem_no_current = -1
    var current_user_id_amplify =""

    fun get_profile_data(){
        var uid = FirebaseAuth.getInstance().currentUser?.uid

        viewModelScope.launch {

            Amplify.DataStore.query(User::class.java,Where.matches(User.GMAIL_ID.eq(uid)),{
                    items->
                while(items.hasNext()){
                    val item = items.next()
                    mutable_profile_data.postValue(item)

                    degree_id_current = item.userDegreeId
                    sem_no_current = item.semNo
                    current_user_id_amplify = item.id

                    Amplify.DataStore.query(Degree::class.java,Where.matches(Degree.ID.eq(item.userDegreeId)),{
                        ditems->

                           while(ditems.hasNext()){
                               val ditem = ditems.next()
                               mutable_degree_data.postValue(ditem.dname)
                               degree_id_current = ditem.id

                               get_subject_data()
                           }

                    },{
                        Log.e("debug:","no network")
                        get_toast("Unable to get data")
                    })

                }


            },{
                Log.e("debug:","no network")
                get_toast("Unable to get data")
            })

        }
    }


    fun get_subject_data(){
        viewModelScope.launch {
            Amplify.DataStore.query(
                Subject::class.java,
                Where.matches(
                    Subject.SUBJECT_DEGREE_ID.eq(degree_id_current).and(Subject.SEM_NO.eq(sem_no_current))
                ),
                { subjects ->
                    subjects_arr = ArrayList<my_subject_model>()
                    while (subjects.hasNext()) {
                        val item = subjects.next()
                       subjects_arr.add(my_subject_model(item.subName,item.id))
                    }
                    mutable_subject_data.postValue(subjects_arr)
                },
                {
                    Log.e("debug:", "no network")
                    get_toast("Unable to get data")
                })

        }
    }

    //give me subject i will give you topics
    var subject_chosen = ""
    var subject_chosen_id =""

    fun get_topic_data(subject_pos:Int){

        subject_chosen = subjects_arr.get(subject_pos).sname
        subject_chosen_id = subjects_arr.get(subject_pos).id


        viewModelScope.launch {
            Amplify.DataStore.query(
                Topic::class.java, Where.matches(
                    Topic.TOPIC_SUBJECT_ID.eq(subjects_arr.get(subject_pos).id))
            ,{
                topic_item->
                        topic_arr = ArrayList()
                    while(topic_item.hasNext()){
                        val item = topic_item.next()
                            topic_arr.add(my_topic_model(item.id,item.topicName,item.timer,0))
                    }
                    mutable_topic_data.postValue(topic_arr)
                    count_test_completed_for_a_topic()
                },{
                    Log.e("debug:", "no network")
                    get_toast("Unable to get data")
                }
                )
        }
    }


    fun initalize_response_arr(size:Int){
        arr_user_response = ArrayList<Int>()
        for(i in 0..(size-1)){
            arr_user_response.add(-1)
        }
    }

    //give me a topic i will give you question bro
    var current_topic_num =-1
    var curr_topic_id =""

    fun get_questions(topic_pos:Int) {
        current_topic_num = topic_pos
        curr_topic_id = topic_arr[topic_pos].id

        viewModelScope.launch {
            Amplify.DataStore.query(
                Question::class.java,
                Where.matches(Question.QUESTION_TOPIC_ID.eq(topic_arr.get(topic_pos).id)).sorted(Question.QNUM.ascending()),
                {
                questionitems ->
                    question_arr = ArrayList()
                    while(questionitems.hasNext()){
                        val item = questionitems.next()
                        question_arr.add(my_question_model(item.id,item.qDes,item.option1,item.option2,item.option3,item.option4))
                    }
                    get_all_answer_of_topic(topic_pos)
                    mutable_question_data.postValue(question_arr)
                },
                {
                    Log.e("debug:", "no network")
                    get_toast("Unable to get data")
                })
        }
    }


    //give it a topic it will give you answers


    fun get_all_answer_of_topic(topic_pos:Int) {
        viewModelScope.launch {

            Amplify.DataStore.query(
                Answer::class.java,
                Where.matches(Answer.ANSWER_TOPIC_ID.eq(topic_arr.get(topic_pos).id)),
                { answer_itr ->
                    answers_arr = ArrayList()
                    while (answer_itr.hasNext()) {
                        val item = answer_itr.next()
                       answers_arr.addAll(item.answerList)
                      }
                    Log.d("debug:","test3 --> all the answers i got from server is --> ${answers_arr.toString()}")
                },
                {
                    Log.e("debug:", "no network")
                    get_toast("Unable to get data")
                })

        }
    }



    ////////////////////////////////////this code is for timer//////////////////////////////////////////////

    private val mutable_timer =MutableLiveData<String>()
    val timer_live_data :LiveData<String> get() = mutable_timer

    private val mutable_timer_min =MutableLiveData<String>()
    val timer_live_data_min :LiveData<String> get() = mutable_timer_min

    private val mutable_flag_to_end_test = MutableLiveData<Int>()
    val flag_live_to_finish_test : LiveData<Int> get() = mutable_flag_to_end_test
    //it recalls it self


    lateinit var my_timer :CountDownTimer
    var value_of_timer_when_ended = 0

    fun start_timer(minutes :Int){
        var minss= minutes
        var sixty_sec =59

        mutable_timer_min.postValue("${--minss}")

        viewModelScope.launch {
       my_timer =     object : CountDownTimer((minutes*1000*60).toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) { //
                    mutable_timer.postValue("${--sixty_sec}")
                    Log.d("debug:","counter seconds --> $sixty_sec")

                    if(sixty_sec==0){
                        if(minss==0){
                            onFinish()
                          this.cancel()
                            minss++
                        }
                        mutable_timer_min.postValue("${--minss}")
                        sixty_sec = 59
                    }
                }

                override fun onFinish() {
                        value_of_timer_when_ended = minss*60 + sixty_sec
                        mutable_flag_to_end_test.postValue(flag_finish_code)  //it pops a fragment
                }
            }.start()

        }

    }



    fun stop_timer(){
        my_timer.onFinish()
       my_timer.cancel()

    }









    //give my a user and a unique topic
    //i will give you number of test he has completed

    private val mutable_count_test_completed_for_a_topic = MutableLiveData<Int>()
    val live_count_test_completed_for_a_topic : LiveData<Int> get() = mutable_count_test_completed_for_a_topic


    private val mutable_previous_result = MutableLiveData<List<Result>>()
    val live_previous_result :LiveData<List<Result>> get() = mutable_previous_result

  lateinit  var previous_result : ArrayList<Result>

    fun count_test_completed_for_a_topic(){

if(topic_arr.size >1) {
    viewModelScope.launch {
        Amplify.DataStore.query(
            Result::class.java,
            Where.matches(Result.RESULT_SUBJECT_ID.eq(subject_chosen_id).and(Result.RESULT_USER_ID.eq(current_user_id_amplify))),
            { all_result ->
                previous_result = ArrayList()

                var count = 0
                var temp_arr = ArrayList<String>()
                while (all_result.hasNext()) {
                    val item = all_result.next()
                    temp_arr.add(item.resultTopicId)
                    previous_result.add(item)
                    count++
                }
                val list_without_duplicates = temp_arr.distinct()
                mutable_count_test_completed_for_a_topic.postValue(list_without_duplicates.size)
                mutable_previous_result.postValue(previous_result)

            },
            {
                Log.e("debug:", "no network")
                get_toast("Unable to get data")
            })
    }
}else{
    mutable_count_test_completed_for_a_topic.postValue(0)
    mutable_previous_result.postValue(ArrayList<Result>())
}
    }




}