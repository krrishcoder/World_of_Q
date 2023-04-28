package com.krrishshx.worldofq.adapters

import androidx.recyclerview.widget.DiffUtil

import com.krrishshx.worldofq.model.TestSeries
import com.krrishshx.worldofq.model.my_topic_model

class TestTopicsDiffUtil(private val oldList:List<my_topic_model>, private val newList:List<my_topic_model>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

            return oldList?.get(oldItemPosition)?.id == newList?.get(newItemPosition)?.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList?.get(oldItemPosition)?.topic_name == newList?.get(newItemPosition)?.topic_name
        }
    }

