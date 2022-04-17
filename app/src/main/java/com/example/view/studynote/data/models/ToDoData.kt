package com.example.view.studynote.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.view.studynote.data.models.Priority
import kotlinx.parcelize.Parcelize
//import kotlinx.android.parcel.Parcelize
//데이터관련
@Entity(tableName = "todo_table")
@Parcelize
data class ToDoData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
): Parcelable
