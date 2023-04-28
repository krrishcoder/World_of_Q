package com.krrishshx.worldofq.frags

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.krrishshx.worldofq.adapters.ViewPager_adapter_question_display
import com.krrishshx.worldofq.databinding.FragmentFragQuestionDisplayBinding
import com.krrishshx.worldofq.view_models.Home_vm
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Frag_question_display : Fragment() , ViewPager_adapter_question_display.option_selected_listener,
    ViewPager_adapter_question_display.test_finish_listener, ViewPager_adapter_question_display.mNext_previous_listener

{

    lateinit var binding: FragmentFragQuestionDisplayBinding
    lateinit var viewpager_adapter : ViewPager_adapter_question_display
    private val vm : Home_vm by activityViewModels()
    lateinit var activity: Activity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragQuestionDisplayBinding.inflate(inflater, container, false)
        viewpager_adapter = ViewPager_adapter_question_display(this,this,this)

        if (isAdded()) {
            // Access activity or its views here


            vm.question_live_data.observe(requireActivity(), Observer {
                vm.initalize_response_arr(it.size)
                viewpager_adapter.setData(it)

                vm.get_attempt_num(vm.subject_chosen, vm.current_user_id_amplify)
            })
            Log.d(
                "debug:",
                "back stack entry count  in frag question display --> ${parentFragmentManager.backStackEntryCount}"
            )


            binding.viewpagerQuestionDisplay.adapter = viewpager_adapter

            vm.timer_live_data.observe(requireActivity(), Observer {
                binding.tvSecondsLeft.text = it
            })

            vm.timer_live_data_min.observe(requireActivity(), Observer {
                binding.tvMinuteLeft.text = it
            })



            vm.flag_live_to_finish_test.observe(requireActivity(), Observer {

                if (it >= vm.flag_finish_code) {
                    vm.flag_finish_code += 1

                    vm.create_result(vm.subject_chosen,vm.current_user_id_amplify,vm.value_of_timer_when_ended)
                     vm.update_streak_count()

                    Log.d("Debug:", "pops a fragment -- > when test called finish  in Fragment ")
                    //1st exit point
                }

            })

        }else{
            Log.e("debug:","not attached to activity")
        }
     return binding.root
    }

    override fun option_selected(question_number: Int, option_selected: Int) {
        //Log.d("debug:","question no. --> $question_number option selected--> $option_selected   size of answer --> ${vm.arr_user_response.size}")
        vm.arr_user_response.set(question_number,option_selected)
    //   Log.d("debug:"," see the response array -> ${vm.arr_user_response.toString()}")
    }


    override fun on_test_finish(position: Int) {

//        AlertDialog.Builder(requireContext())
//            .setTitle("Confirm Exit Test")
//            .setMessage("Are you sure you want to finish the test here?")
//            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
//                Toast.makeText(requireContext(),"Test Submitted",Toast.LENGTH_SHORT).show()
//
//                vm.stop_timer()
//
//                requireActivity().supportFragmentManager.popBackStack()
//
//            })
//            .setNegativeButton("No", null)
//            .show()
    }

    override fun on_next_previous_clicked(flag: Int) {
        if(flag==1){
            binding.viewpagerQuestionDisplay.currentItem = binding.viewpagerQuestionDisplay.currentItem + 1
        }
        if(flag==-1){
            binding.viewpagerQuestionDisplay.currentItem = binding.viewpagerQuestionDisplay.currentItem - 1

        }

    }


}