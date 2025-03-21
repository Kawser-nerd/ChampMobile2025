package com.example.employeermdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Changed from 100 to 0 to allow Room to auto-generate
    val name: String,
    val age: Int,
    val gender: Boolean, // true for male, false for female
    val city: String
)