package com.example.employeermdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDatabase:RoomDatabase() {
    // invoke the dao
    abstract fun employeeDao(): EmployeeDao

    companion object{
        @Volatile
        private var INSTANCE:EmployeeDatabase? = null
    // build the database
        fun getDatabase(context: Context):EmployeeDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDatabase::class.java,
                    "employee_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}