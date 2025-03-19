package com.example.firstapp

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // get the resource button
        val regBtn = findViewById<Button>(R.id.Submit)
        val nameInput = findViewById<EditText>(R.id.name)
        val emailInput = findViewById<EditText>(R.id.emailAddress)
        val password = findViewById<EditText>(R.id.passwd)
        val male = findViewById<CheckBox>(R.id.Male)
        val female = findViewById<CheckBox>(R.id.Female)
        var gender:String = ""
        // radio group// radio button
        val radio_group = findViewById<RadioGroup>(R.id.cityfrom)

        // radioGroup Listener
        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener{group, checkedID ->
                val radioValue:RadioButton = findViewById(checkedID)
                Toast.makeText(this, "On Changed: ${radioValue.text}",
                    Toast.LENGTH_LONG).show()

            }
        )

        // checkbox listerner
        male.setOnClickListener{
            if(male.isChecked) {
                gender = "Male"
                female.isChecked = false
            }
        }
        female.setOnClickListener{
            if(female.isChecked) {
                gender = "Female"
                male.isChecked = false
            }
        }
        /*
        nameInput.setOnClickListener{
            clearEditText(nameInput)
        }*/

        // Touch the EditText
        nameInput.setOnTouchListener{_,event ->
            if (event.action == MotionEvent.ACTION_DOWN){
                clearEditText(nameInput)
            }
            false
        }

        emailInput.setOnTouchListener{_,event ->
            if (event.action == MotionEvent.ACTION_DOWN){
                clearEditText(emailInput)
            }
            false
        }

        // Event handler function
        regBtn.setOnClickListener{
            val name = nameInput.text // datatype will be assigned dynamically
            Toast.makeText(this, name, Toast.LENGTH_LONG).show() // debug the state of the application

            // radio button
            var id: Int = radio_group.checkedRadioButtonId
            if(id != -1){ // one id is selected
                val radioValue:RadioButton = findViewById(id)
                Toast.makeText(this, "Radiobutton value: ${radioValue.text}",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

}
fun clearEditText(editText: EditText){
    editText.text.clear()
}

