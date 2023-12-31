package com.example.machineproject_mobdeve_mercado_sillona_park

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UpdateViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_edit_view)

        var confirmBtn: Button = findViewById(R.id.add_Confirm_Btn)
        var backBtn: Button = findViewById(R.id.add_Back_Btn)
        var removeBtn: Button = findViewById(R.id.add_Remove_Btn)
        var dummyBtn: Button = findViewById(R.id.add_Dummy_Btn)

        var codeEtv: EditText = findViewById(R.id.add_Code_Etv)
        var sectionEtv: EditText = findViewById(R.id.add_Section_Etv)
        var timeEtv: EditText = findViewById(R.id.add_Time_Etv)
        var roomEtv: EditText = findViewById(R.id.add_Room_Etv)
        var dayEtv: EditText = findViewById(R.id.add_Day_Etv)

        codeEtv.setText(intent.getStringExtra("code"))
        sectionEtv.setText(intent.getStringExtra("section"))
        timeEtv.setText(intent.getStringExtra("time"))
        roomEtv.setText(intent.getStringExtra("room"))
        dayEtv.setText(intent.getStringExtra("day"))

        var context= this
        var db = DataBaseHandler(context)

        confirmBtn.setOnClickListener{
            if (codeEtv.text.toString().length > 0 && sectionEtv.text.toString().length > 0 && timeEtv.text.toString().length > 0 && roomEtv.text.toString().length > 0 && dayEtv.text.toString().length > 0){
                var course = Course(codeEtv.text.toString().uppercase(), sectionEtv.text.toString().uppercase(), roomEtv.text.toString().uppercase(), timeEtv.text.toString(), dayEtv.text.toString().uppercase())

                // todo Update the Database
                db.updateData(course)

                Toast.makeText(context, "UPDATED to DB", Toast.LENGTH_SHORT).show()
                var intentLoadNextActivity = Intent(this, CourseViewActivity::class.java)
                startActivity(intentLoadNextActivity)
            }
            else
                Toast.makeText(context, "Some of the input fields are blank!", Toast.LENGTH_SHORT).show()
        }

        backBtn.setOnClickListener{
            val intentLoadNextActivity = Intent(this, CourseViewActivity::class.java)
            startActivity(intentLoadNextActivity)
        }

        removeBtn.setOnClickListener{
            db.deleteData()
            Toast.makeText(context, "All data removed!", Toast.LENGTH_SHORT).show()

        }

        dummyBtn.setOnClickListener{
            db.initializeData()
        }

    }
}