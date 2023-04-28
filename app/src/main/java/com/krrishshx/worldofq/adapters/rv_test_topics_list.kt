package com.krrishshx.worldofq.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.krrishshx.worldofq.databinding.ItemInTestSeriesBinding
import com.krrishshx.worldofq.model.TestSeries
import com.krrishshx.worldofq.model.my_topic_model

class rv_test_topics_list(private  val listener : MytestTopicsOnItemClickListener) : RecyclerView.Adapter<rv_test_topics_list.ViewHolder>() {

    private var dataSet = emptyList<my_topic_model>()

    inner class ViewHolder(val binding: ItemInTestSeriesBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, v)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemInTestSeriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.binding.tvQuestionNo.text = "${(position + 1)} ."
        holder.binding.tvTopicName.text = dataSet.get(position)?.topic_name
//        holder.binding.tvNoOfQuestionInTopic.text = "wait"
        holder.binding.tvTimeForATopic.text =
            "${dataSet.get(position).topic_time.toString()} min"

        if (position == (dataSet.size - 1)) {
            val layoutParams = MarginLayoutParams(holder.binding.root.getLayoutParams())
            layoutParams.setMargins(
                layoutParams.leftMargin,
                layoutParams.topMargin+40,
                layoutParams.rightMargin,
                40
            )

            holder.binding.root.setLayoutParams(layoutParams)

        }
    }

        override fun getItemCount(): Int {
            return dataSet.size
        }

        fun setData(new_question_list: List<my_topic_model>) {


            val diffUtil = TestTopicsDiffUtil(dataSet, new_question_list)
            val diffResults = DiffUtil.calculateDiff(diffUtil)
            dataSet = new_question_list
            diffResults.dispatchUpdatesTo(this)
        }


        interface MytestTopicsOnItemClickListener {
            fun onItemClick(position: Int, v: View?)
        }



}


