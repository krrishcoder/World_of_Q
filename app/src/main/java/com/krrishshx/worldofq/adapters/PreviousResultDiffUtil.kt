package com.krrishshx.worldofq.adapters

import androidx.recyclerview.widget.DiffUtil


class PreviousResultDiffUtil (private val oldList:List<com.amplifyframework.datastore.generated.model.Result>, private val newList: List<com.amplifyframework.datastore.generated.model.Result>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

       return oldList.get(oldItemPosition).createdAt == newList.get(newItemPosition).createdAt
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

            return oldList.get(oldItemPosition).id == newList.get(newItemPosition).id

    }
}