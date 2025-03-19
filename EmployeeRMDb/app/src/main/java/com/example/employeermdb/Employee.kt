package com.example.employeermdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 100, // initial employee id
    val name:String,
    val age:Int,
    val gender:Boolean,
    val city:String
)
