package com.example.employeermdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EmployeeDao {
    @Insert
    suspend fun insertEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("Select * From employee_table")
    suspend fun getAllEmployee():List<Employee>

    // id based search
    @Query("Select * From employee_table where id = :id")
    suspend fun getEmployeeById(id: Int):Employee?
}