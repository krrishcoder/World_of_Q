package com.krrishshx.worldofq.adapters

import androidx.recyclerview.widget.DiffUtil
import com.krrishshx.worldofq.model_x.QResponse

class QuestionDiffUtil(private val oldList:QResponse,private val newList:QResponse) :DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldList.get(oldItemPosition).qid == newList.get(newItemPosition).qid
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).ques == newList.get(newItemPosition).ques
    }
}