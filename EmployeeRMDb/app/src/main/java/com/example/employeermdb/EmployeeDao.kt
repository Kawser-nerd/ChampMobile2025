package com.example.employeermdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Insert
    suspend fun insertEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("SELECT * FROM employee_table")
    fun getAllEmployees(): Flow<List<Employee>> // Changed to Flow for reactive updates

    // id based search
    @Query("SELECT * FROM employee_table WHERE id = :id")
    suspend fun getEmployeeById(id: Int): Employee?
}
