package com.krrishshx.worldofq.frags

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.krrishshx.worldofq.adapters.rv_subject_adapter
import com.krrishshx.worldofq.databinding.FragmentFragProfileBinding
import com.krrishshx.worldofq.view_models.Home_vm
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Frag_Profile() : Fragment() , rv_subject_adapter.MySubjectOnItemClickListener{

    lateinit var binding : FragmentFragProfileBinding
    private val vm : Home_vm by activityViewModels()

    private var mOnButtonClickListener: Frag_Profile.OnButtonClickListener? = null

    lateinit var rv_adapter :rv_subject_adapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            mOnButtonClickListener = context as OnButtonClickListener
        }catch (e :java.lang.Exception){
            Log.d("debug:","${e.message}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragProfileBinding.inflate(inflater,container,false)
        rv_adapter = rv_subject_adapter(this)

        binding.rvSubjects.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
        binding.rvSubjects.adapter = rv_adapter

        Log.d("debug:","back stack entry count for fraf profile --> ${parentFragmentManager.backStackEntryCount}")

        vm.profile_live_data.observe(requireActivity(), Observer {
            vm.get_streak_count_from_server(it.id)
            binding.profileParent.tvUserName.text = it.username.toString()
            binding.profileParent.userRollNo.text = it.rollno.toString()
            binding.profileParent.userSemNo.text = "   sem - ${it.semNo}"


        })

        vm.subject_live_data.observe(requireActivity(), Observer {
            rv_adapter.setData(it)
        })

        vm.streak_live_data.observe(requireActivity(), Observer {
            binding.profileParent.noOfStreak.text = "$it"
        })


        vm.degree_live_data.observe(requireActivity(), Observer {
            binding.profileParent.userDegreeName.text = it.toString()

        })

        return binding.root
    }

    override fun onItemClick(position: Int, v: View?) {
        Log.d("debug:","clicked at subject")
        mOnButtonClickListener?.onButtonClicked(position,v)


    }

    internal interface OnButtonClickListener {
        fun onButtonClicked(position: Int,view: View?)
    }

}