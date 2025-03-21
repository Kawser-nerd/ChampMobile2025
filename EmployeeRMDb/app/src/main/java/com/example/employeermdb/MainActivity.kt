package com.example.employeermdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.employeermdb.ui.theme.EmployeeRMDbTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmployeeRMDbTheme {
                EmployeeRegistrationScreen()
            }
        }
    }
}

@Composable
fun EmployeeRegistrationScreen() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf(18) }
    var gender by remember { mutableStateOf(true) } // true for male, false for female
    var city by remember { mutableStateOf("Montreal") }
    var employeeId by remember { mutableStateOf(0) }
    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val employeeDao = EmployeeDatabase.getDatabase(context).employeeDao()
    val viewModel: EmployeeViewModel = viewModel(factory = EmployeeViewModelFactory(employeeDao))

    // Define the city options
    val cities = listOf("Montreal", "Ottawa", "Toronto", "Edmonton", "Longueil")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Register or Update Employee",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        ) {
            // Name input
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Age Spinner (Dropdown)
            val ageOptions = List(100) { it + 18 }
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Button to trigger the dropdown visibility
                Button(onClick = { expanded = true }) {
                    Text(text = "Select Age: $age")
                }

                // Dropdown Menu
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Dropdown items (ages)
                    ageOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text("$option") },
                            onClick = {
                                age = option
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Gender Checkbox
            Row {
                Checkbox(checked = gender, onCheckedChange = { gender = it })
                Text(text = "Male", Modifier.padding(top = 12.dp))
                Spacer(modifier = Modifier.width(16.dp))
                Checkbox(checked = !gender, onCheckedChange = { gender = !it })
                Text(text = "Female", Modifier.padding(top = 12.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // City Radio Buttons
            Text(text = "City:", style = MaterialTheme.typography.bodyLarge)
            cities.forEach { cityOption ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = city == cityOption,
                        onClick = { city = cityOption }
                    )
                    Text(text = cityOption)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons for Add, Update, and Delete Employee
            Row {
                Button(onClick = {
                    if (employeeId == 0) {
                        viewModel.addEmployee(name, age, gender, city)
                    } else {
                        viewModel.updateEmployee(employeeId, name, age, gender, city)
                    }
                }) {
                    Text("Save Employee")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    employeeId = 0
                    name = ""
                    age = 18
                    gender = true
                    city = "Montreal" // Changed to an available city
                }) {
                    Text("Clear")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // List of Employees
            val employees by viewModel.employees.collectAsState(initial = emptyList())

            Text(text = "Employee List:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(employees) { employee ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = "Employee: ${employee.name}, ${employee.age}, " +
                                    "${if (employee.gender) "Male" else "Female"}, ${employee.city}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Row {
                            Button(onClick = {
                                employeeId = employee.id
                                name = employee.name
                                age = employee.age
                                gender = employee.gender
                                city = employee.city
                            }) {
                                Text("Edit")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(onClick = {
                                viewModel.deleteEmployee(employee)
                            }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmployeeUIPreview() {
    EmployeeRMDbTheme {
        EmployeeRegistrationScreen()
    }
}