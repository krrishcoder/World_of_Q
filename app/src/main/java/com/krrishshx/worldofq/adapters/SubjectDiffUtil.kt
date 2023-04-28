package com.krrishshx.worldofq.adapters

import androidx.recyclerview.widget.DiffUtil
import com.amplifyframework.datastore.generated.model.Subject
import com.krrishshx.worldofq.model.my_subject_model

class SubjectDiffUtil(private val oldList:List<my_subject_model> , private val newList:List<my_subject_model>) :
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
            return oldList.get(oldItemPosition).sname == newList.get(newItemPosition).sname
        }
    }
