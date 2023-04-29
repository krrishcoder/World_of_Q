package com.krrishshx.worldofq.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.krrishshx.worldofq.databinding.ItemInPreviousResultBinding
import com.krrishshx.worldofq.model.my_topic_model

const val TAG ="debug:"
class rv_prevoius_result_adapter(private  val listener :MyPreviousResultOnItemClickListener,private val scrolling_listener : startScrollingListener) : RecyclerView.Adapter<rv_prevoius_result_adapter.ViewHolder>() {

        private var dataSet = emptyList<com.amplifyframework.datastore.generated.model.Result>()
       private var data_topics = emptyList<my_topic_model>()
       private var data_topics_id = ArrayList<String>()


        inner class ViewHolder(val binding: ItemInPreviousResultBinding) :
            RecyclerView.ViewHolder(binding.root), View.OnClickListener {
            init {
                binding.root.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                val position: Int = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onResultItemClick(position, v)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                ItemInPreviousResultBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.binding.tvScore.text = "${dataSet?.get(position)?.attendedQuestion }/${dataSet.get(position)?.totalMarks } "
            holder.binding.tvAttemptNo.text = dataSet?.get(position)?.attemptNo?.toString()
            val str = dataSet?.get(position)?.createdAt?.toDate()?.toString()

            val newStr = str?.substring(0, str.length - 14)
            holder.binding.tvDate.text = newStr

if(position >=4){
    scrolling_listener.onstartScrolling(position)
    Log.d(TAG,"test3  --> position more than 4 binded position --> ${position}")
}

            val curr_topic_id =dataSet?.get(position)?.resultTopicId

            for(i in 0 ..(data_topics_id.size-1)){

                if(curr_topic_id == data_topics_id[i]){
                     holder.binding.resultTopic.text  = data_topics[i]?.topic_name
                     break
                }
            }
        }

        override fun getItemCount(): Int {
            return dataSet.size
        }

        fun setData(new_list: List<com.amplifyframework.datastore.generated.model.Result>,topic_arr:ArrayList<my_topic_model>) {
            val diffUtil =PreviousResultDiffUtil(dataSet, new_list)
            val diffResults = DiffUtil.calculateDiff(diffUtil)
            dataSet = new_list
            data_topics = topic_arr


            for(i in 0..(topic_arr.size-1)){
                topic_arr[i]?.id?.let { data_topics_id.add(it) }
            }

            diffResults.dispatchUpdatesTo(this)

        }


        interface MyPreviousResultOnItemClickListener {
            fun onResultItemClick(position: Int, v: View?)
        }

    interface startScrollingListener{
        fun onstartScrolling(position: Int)
    }



    }

