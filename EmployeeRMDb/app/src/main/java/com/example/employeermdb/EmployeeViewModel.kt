package com.example.employeermdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class EmployeeViewModel(private val employeeDao: EmployeeDao) : ViewModel() {
    // Get employee list as Flow directly from DAO
    val employees: Flow<List<Employee>> = employeeDao.getAllEmployees()

    // Add employee function
    fun addEmployee(name: String, age: Int, gender: Boolean, city: String) {
        viewModelScope.launch {
            val employee = Employee(name = name, age = age, gender = gender, city = city)
            employeeDao.insertEmployee(employee)
        }
    }

    fun updateEmployee(empId: Int, name: String, age: Int, gender: Boolean, city: String) {
        viewModelScope.launch {
            val employee = Employee(id = empId, name = name, age = age, gender = gender, city = city)
            employeeDao.updateEmployee(employee)
        }
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch {
            employeeDao.deleteEmployee(employee)
        }
    }

    suspend fun getEmployeeById(empId: Int): Employee? {
        return employeeDao.getEmployeeById(empId)
    }
}