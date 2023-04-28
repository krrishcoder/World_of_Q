package com.krrishshx.worldofq.adapters

import androidx.recyclerview.widget.DiffUtil
import com.krrishshx.worldofq.model.my_question_model


class QuestionDiffutilViewPager(private val oldList: List<my_question_model>, private val newList: List<my_question_model>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

            return oldList.get(oldItemPosition).id == newList.get(newItemPosition).id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList.get(oldItemPosition).question_des == newList.get(newItemPosition).question_des
        }
    }
