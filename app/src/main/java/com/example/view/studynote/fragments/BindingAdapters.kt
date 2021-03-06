package com.example.view.studynote.fragments

import android.os.Build
import android.view.View
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.view.studynote.R
import com.example.view.studynote.data.models.Priority
import com.example.view.studynote.data.models.ToDoData
import com.example.view.studynote.fragments.list.ListFragmentDirections
import com.example.view.studynote.fragments.update.UpdateFragmentArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapters {


    companion object{

        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean){
            view.setOnClickListener{
                if(navigate){
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }
        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>){
            when(emptyDatabase.value){
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
                else->{}
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView: CardView,priority: Priority){
            when(priority){
                Priority.HIGH -> {cardView.setCardBackgroundColor(cardView.context.getColor(R.color.red))}
                Priority.MEDIUM -> {cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))}
                Priority.LOW -> {cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))}
            }
        }

        @BindingAdapter("android:sendDataToUpdateFragment")
        @JvmStatic
        fun sendDataToupdateFragment(view: ConstraintLayout, currentItem: ToDoData){
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            view.findNavController().navigate(action)
        }


//        @BindingAdapter("android:parsePriorityToint")
//        @JvmStatic
//        fun parsePriorityToInt(view: Spinner,priority: Priority){
//            when(priority){
//                Priority.HIGH -> {view.setSelection(0)}
//                Priority.MEDIUM -> {view.setSelection(1)}
//                Priority.LOW ->{view.setSelection(2)}
//            }
//        }
    }
}