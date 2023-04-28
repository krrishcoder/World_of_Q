package com.krrishshx.worldofq.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.krrishshx.worldofq.databinding.QuestionItemWithBtnBinding
import com.krrishshx.worldofq.model_x.QResponse

class rv_qitem_adapter(): RecyclerView.Adapter<rv_qitem_adapter.ViewHolder>() {

        private var dataSet = QResponse()

        inner  class ViewHolder(val binding: QuestionItemWithBtnBinding) : RecyclerView.ViewHolder(binding.root){


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            return ViewHolder(QuestionItemWithBtnBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.tvQuesNumber.text = "${position+1}. "
    holder.binding.tvQuestionMain.text = dataSet.get(position).ques


    //this will be automated on number of questions
    holder.binding.checkBox.text = dataSet.get(position).options.get(0)
    holder.binding.checkBox2.text = dataSet.get(position).options.get(1)
    holder.binding.checkBox3.text = dataSet.get(position).options.get(2)
    holder.binding.checkBox4.text = dataSet.get(position).options.get(3)
    }



        override fun getItemCount(): Int {
            return dataSet.size
        }

        fun setData(new_question_list: QResponse) {

            val diffUtil = QuestionDiffUtil(dataSet,new_question_list)
            val diffResults = DiffUtil.calculateDiff(diffUtil)
            dataSet = new_question_list
            diffResults.dispatchUpdatesTo(this)
        }
    }
