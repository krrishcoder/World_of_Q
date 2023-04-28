package com.krrishshx.worldofq.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import com.krrishshx.worldofq.databinding.ItemABinding
import com.krrishshx.worldofq.databinding.QuestionItemWithBtnBinding
import com.krrishshx.worldofq.model_x.QResponse

class rv_qlist_adapter(private  val listener : MyOnItemClickListener) : RecyclerView.Adapter<rv_qlist_adapter.ViewHolder>() {

    private var dataSet = QResponse()


   inner class ViewHolder(val binding:ItemABinding) : RecyclerView.ViewHolder(binding.root) , View.OnClickListener {
init{
    binding.root.setOnClickListener(this)
}

        override fun onClick(v: View?) {
            val position:Int = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position,v)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(ItemABinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.tvQno.text = "Q.${position+1}"
        holder.binding.tvStatus.text ="opened"
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(new_question_list:QResponse) {
        val diffUtil = QuestionDiffUtil(dataSet,new_question_list)
        val diffResults = DiffUtil.calculateDiff(diffUtil)

        dataSet = new_question_list

        diffResults.dispatchUpdatesTo(this)
    }

    interface MyOnItemClickListener{
        fun onItemClick(position:Int, v: View?)
    }
}