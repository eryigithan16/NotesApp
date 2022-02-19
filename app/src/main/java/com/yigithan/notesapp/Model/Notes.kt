package com.yigithan.notesapp.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Notes")
@Parcelize
class Notes (
    @PrimaryKey(autoGenerate = true)
    var noteId: Int? = null,
    var title: String,
    var subTitle: String,
    var dateTime: String,
    var noteText: String,
    var priority: String,

) : Parcelable
