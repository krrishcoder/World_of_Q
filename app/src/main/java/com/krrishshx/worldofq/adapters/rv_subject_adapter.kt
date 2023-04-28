package com.krrishshx.worldofq.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.Subject
import com.krrishshx.worldofq.databinding.SubjectSelectionItemBinding
import com.krrishshx.worldofq.model.my_subject_model

class rv_subject_adapter(private  val listener : MySubjectOnItemClickListener) : RecyclerView.Adapter<rv_subject_adapter.ViewHolder>() {

        private var dataSet = emptyList<my_subject_model>()


        inner class ViewHolder(val binding: SubjectSelectionItemBinding) : RecyclerView.ViewHolder(binding.root) , View.OnClickListener {
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
        return ViewHolder(SubjectSelectionItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.subjectName.text = dataSet.get(position).sname
        holder.binding.testGiven.text = "wait"
    }


        override fun getItemCount(): Int {
            return dataSet.size
        }

        fun setData(new_question_list:List<my_subject_model>) {
            val diffUtil = SubjectDiffUtil(dataSet,new_question_list)
            val diffResults = DiffUtil.calculateDiff(diffUtil)

            dataSet = new_question_list



            diffResults.dispatchUpdatesTo(this)
        }

        interface MySubjectOnItemClickListener{
            fun onItemClick(position:Int, v: View?)
        }




}
