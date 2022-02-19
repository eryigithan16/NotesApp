package com.yigithan.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yigithan.notesapp.dao.NoteDao
import com.yigithan.notesapp.Model.Notes

@Database(entities = [Notes::class],version = 1,exportSchema = false)
abstract class NotesDatabase:RoomDatabase() {

    abstract fun noteDao():NoteDao

    companion object{
        @Volatile
        var notesDatabase:NotesDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): NotesDatabase{
            val tempInstance = notesDatabase

            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance =
                    Room.databaseBuilder(
                        context,
                        NotesDatabase::class.java,
                        "Notes"
                    ).allowMainThreadQueries().build()
                notesDatabase = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }

}