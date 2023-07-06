package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var btnDatePicker : Button = findViewById(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener {view->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view : View){

        val calendar : Calendar = Calendar.getInstance()
        val currentYear : Int = calendar.get(Calendar.YEAR)
        val currentMonth : Int = calendar.get(Calendar.MONTH)
        val currentDay : Int = calendar.get(Calendar.DAY_OF_MONTH)

//        DatePickerDialog is used to Select Dates.. Its Constuctor takes 5 parameters...
//        That are 1.context(Line no 16),   2.listener(Line no.38 to 75)   3.year(Line no.76)   4. month(Line no.77)   5. day(Line no. 78)
       val dpd =  DatePickerDialog(this,
            {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val theDate : String = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                val tvSelectedDate : TextView = findViewById(R.id.tvSelectedDate)
                tvSelectedDate.setText(theDate)

                val sdf : SimpleDateFormat= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

//                Calculated total minutes from 1970 to Selected Date
                val selectedDate : Date = sdf.parse(theDate)
                val selectedDateInMinutes : Long = selectedDate!!.time / 60000

//                Calculated total minutes from 1970 to Current Date
                val currentDate : Date = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes : Long = currentDate!!.time / 60000

//                The Differnce between current and selected will be out age in minutes
                val ageInMinutes : Long = currentDateInMinutes-selectedDateInMinutes
                val tvCalculatedAgeInMinutes : TextView = findViewById(R.id.tvCalculatedAgeInMinutes)
                tvCalculatedAgeInMinutes.setText("$ageInMinutes")

                val ageInHours : Long= ageInMinutes/60;
                val tvCalculatedAgeInHours: TextView = findViewById(R.id.tvCalculatedAgeInHours)
                tvCalculatedAgeInHours.setText("$ageInHours")

                val ageInDays : Long = ageInHours/24;
                val tvCalculatedAgeInDays: TextView = findViewById(R.id.tvCalculatedAgeInDays)
                tvCalculatedAgeInDays.setText("$ageInDays")

                val ageInWeeks : Long = ageInDays/7;
                val tvCalculatedAgeInWeeks: TextView = findViewById(R.id.tvCalculatedAgeInWeeks)
                tvCalculatedAgeInWeeks.setText("$ageInWeeks")

                val ageInMonths : Long = ageInDays/30;
                val tvCalculatedAgeInMOnths: TextView = findViewById(R.id.tvCalculatedAgeInMonths)
                tvCalculatedAgeInMOnths.setText("$ageInMonths")

            },
           currentYear,
           currentMonth,
           currentDay
        )

//        Limiting the calender so no one can select future values
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()

    }
}

















