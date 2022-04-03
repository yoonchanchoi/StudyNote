package com.example.view.studynote.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.view.studynote.R
import com.example.view.studynote.data.models.Priority
import com.example.view.studynote.data.models.ToDoData
import com.example.view.studynote.databinding.RowLayoutBinding
import com.example.view.studynote.databinding.RowLayoutBindingImpl
import kotlinx.android.synthetic.main.row_layout.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ToDoData>()

    class MyViewHolder(private val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(todoData: ToDoData){
            binding.toDoData = todoData
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(toDoData: List<ToDoData>){
        this.dataList = toDoData
        notifyDataSetChanged()
    }

//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.itemView.title_txt.text=dataList[position].title
//        holder.itemView.description_txt.text = dataList[position].description
//        holder.itemView.row_background.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
//            holder.itemView.findNavController().navigate(action)
//        }
//
//
//        val priority = dataList[position].priority
//        when(priority){
//            Priority.HIGH -> holder.itemView.priority_indicator.setCardBackgroundColor(ContextCompat.getColor(
//                holder.itemView.context,
//                R.color.red))
//            Priority.MEDIUM -> holder.itemView.priority_indicator.setCardBackgroundColor(ContextCompat.getColor(
//                holder.itemView.context,
//                R.color.yellow))
//            Priority.LOW -> holder.itemView.priority_indicator.setCardBackgroundColor(ContextCompat.getColor(
//                holder.itemView.context,
//                R.color.green))
//        }
//    }

}