package com.krrishshx.worldofq.frags

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.krrishshx.worldofq.R
import com.krrishshx.worldofq.adapters.rv_prevoius_result_adapter
import com.krrishshx.worldofq.adapters.rv_subject_adapter
import com.krrishshx.worldofq.adapters.rv_test_topics_list
import com.krrishshx.worldofq.databinding.DialogBeforeStartingTestBinding
import com.krrishshx.worldofq.databinding.FragmentFragStarterForQBinding
import com.krrishshx.worldofq.view_models.Home_vm
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Frag_starterForQ : Fragment() ,rv_test_topics_list.MytestTopicsOnItemClickListener , rv_prevoius_result_adapter.MyPreviousResultOnItemClickListener{

    lateinit var binding : FragmentFragStarterForQBinding
    lateinit var rv_adapter_topics_list : rv_test_topics_list
    lateinit var rv_adapter_previous_result :rv_prevoius_result_adapter
    lateinit var  dialogBinding : DialogBeforeStartingTestBinding


    var topic_position =0

    lateinit var  myDialog :Dialog
    private val vm : Home_vm by activityViewModels()

    private var mOnStartClickListener: Frag_starterForQ.OnButtonStartTestClickListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)

        try{
        mOnStartClickListener = context as Frag_starterForQ.OnButtonStartTestClickListener
        }catch (e :java.lang.Exception){
            Log.e("debug:","${e.message}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFragStarterForQBinding.inflate(inflater,container,false)
        dialogBinding  = DialogBeforeStartingTestBinding.inflate(inflater,container,false)
        myDialog = Dialog(requireActivity())

        rv_adapter_topics_list = rv_test_topics_list(this)
        rv_adapter_previous_result = rv_prevoius_result_adapter(this)

        binding.rvTestListForASubject.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
        binding.rvPreviousResult.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)


        binding.rvTestListForASubject.adapter = rv_adapter_topics_list
        binding.rvPreviousResult.adapter = rv_adapter_previous_result


        Log.d("debug:","back stack entry count of frag starter --> ${parentFragmentManager.backStackEntryCount}")


        dialogBinding.btnStart.setOnClickListener {

            //its start the test causing problem that at 2nd time in test it does not start the test rather finishes it
            //acts as that i have clicked back button from frag question window

            mOnStartClickListener?.onTestStartClicked(dialogBinding.tvDialogTopicName.text.toString())
            Toast.makeText(requireActivity(),"Test Begins",Toast.LENGTH_SHORT).show()
            vm.get_questions(topic_position)
            vm.start_timer(vm.topic_arr.get(topic_position).topic_time)

            myDialog.cancel()
        }


        vm.live_previous_result.observe(requireActivity(), Observer {
            rv_adapter_previous_result.setData(it,vm.topic_arr)
        })


        vm.topic_live_data.observe(requireActivity(), Observer {

            binding.cardOne.tvSubjectHeading.text = vm.subject_chosen
            binding.cardOne.tvTotalTest.text = it.size?.toString()
            rv_adapter_topics_list.setData(it)



        })

        vm.live_count_test_completed_for_a_topic.observe(requireActivity(), Observer {
            binding.cardOne.tvTestCompletedForATopic.text = "$it"
        })



        return binding.root
    }

    override fun onItemClick(position: Int, v: View?) {
      Log.d("debug:","clicked on test ")

        topic_position = position
        dialogBinding.tvDialogTopicName.text = vm.topic_arr.get(position).topic_name.toString()
        dialogBinding.dialogTimeForTest.text = "${vm.topic_arr.get(position).topic_time.toString()} Min"


        showDialog()



    }

    override fun onResultItemClick(position: Int, v: View?) {

    }

    fun showDialog(){

        myDialog.setContentView(dialogBinding.root)
        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()

    }

    internal interface OnButtonStartTestClickListener {
        fun onTestStartClicked(topic:String)
    }


}