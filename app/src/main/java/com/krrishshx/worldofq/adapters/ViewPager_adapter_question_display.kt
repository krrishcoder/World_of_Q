package com.krrishshx.worldofq.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.krrishshx.worldofq.databinding.ItemInViewpagerQuestionsBinding
import com.krrishshx.worldofq.model.my_question_model

class ViewPager_adapter_question_display(private  val listener_option_selected :option_selected_listener ,private val plistener_test_finish :test_finish_listener,private  val next_previous_listener:mNext_previous_listener) : RecyclerView.Adapter<ViewPager_adapter_question_display.ViewPagerViewHolder>() {

        private var dataSet = emptyList<my_question_model>()

        inner class ViewPagerViewHolder(val binding: ItemInViewpagerQuestionsBinding ) :
            RecyclerView.ViewHolder(binding.root), View.OnClickListener {
            init {
                binding.root.setOnClickListener(this)

            }

            override fun onClick(v: View?) {
//                val position: Int = absoluteAdapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    listener.onItemClick(position, v)
//                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder{
            return ViewPagerViewHolder(
                ItemInViewpagerQuestionsBinding .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        holder.binding.tvQuestionNoVp.text = "Q.${position+1} "
        holder.binding.tvQuestionDes.text = dataSet.get(position).question_des

        holder.binding.cbOne.text = dataSet.get(position).opt1
        holder.binding.cbTwo.text =dataSet.get(position).opt2
        holder.binding.cbThree.text=dataSet.get(position).opt3
        holder.binding.cbFour.text=dataSet.get(position).opt4

        if(position == (dataSet.size-1)){
            holder.binding.btnNextVp.setCardBackgroundColor(Color.MAGENTA)
            holder.binding.tvNext.text = "Submit"
        }


        holder.binding.btnNextVp.setOnClickListener {
            if(position ==(dataSet.size-1)){
              //  plistener_test_finish.on_test_finish(position)
            }else{
                next_previous_listener.on_next_previous_clicked(1)
            }
        }

        holder.binding.btnPreviousVp.setOnClickListener {
            if(position >0){
                next_previous_listener.on_next_previous_clicked(-1)
            }
        }




        holder.binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->



var sel =0
            when(checkedId){
                holder.binding.cbOne.id ->{
                   sel=0
                }
                    holder.binding.cbTwo.id ->{
                        sel =1
                    }
                holder.binding.cbThree.id ->{
                    sel=2
                }
                holder.binding.cbFour.id ->{
                    sel=3
                }
                else ->{

                }

            }
            Log.d("debug:","selected option was $sel")
            listener_option_selected.option_selected(position,sel)

        }


    }

    interface option_selected_listener{
        fun option_selected(question_number :Int,option_selected:Int)
    }

    interface test_finish_listener{
        fun on_test_finish(position: Int)
    }

    interface mNext_previous_listener{
        fun on_next_previous_clicked(flag:Int)
    }


        override fun getItemCount(): Int {
            return dataSet.size
        }

        fun setData(new_list: List<my_question_model>) {
            val diffUtil = QuestionDiffutilViewPager(dataSet, new_list)
            val diffResults = DiffUtil.calculateDiff(diffUtil)

            dataSet = new_list

            diffResults.dispatchUpdatesTo(this)
        }





}
