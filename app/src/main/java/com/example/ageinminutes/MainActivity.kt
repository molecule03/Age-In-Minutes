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

        // Find the button with id btnDatePicker
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)

        // Set a click listener for the button
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    /**
     * Handles the click event of the date picker button.
     * @param view The view that was clicked (in this case, the button).
     */
    fun clickDatePicker(view: View) {

        // Get the current calendar instance
        val calendar: Calendar = Calendar.getInstance()

        // Get the current year, month, and day
        val currentYear: Int = calendar.get(Calendar.YEAR)
        val currentMonth: Int = calendar.get(Calendar.MONTH)
        val currentDay: Int = calendar.get(Calendar.DAY_OF_MONTH)

        // Create a DatePickerDialog to allow the user to select a date
        val dpd = DatePickerDialog(
            this,
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                // Format the selected date as a string
                val theDate: String = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

                // Display the selected date in the TextView
                val tvSelectedDate: TextView = findViewById(R.id.tvSelectedDate)
                tvSelectedDate.text = theDate

                // Create a SimpleDateFormat object to parse the date string
                val sdf: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                // Parse the selected date string into a Date object
                val selectedDate: Date = sdf.parse(theDate)

                // Calculate the total minutes from 1970 to the selected date
                val selectedDateInMinutes: Long = selectedDate!!.time / 60000

                // Calculate the total minutes from 1970 to the current date
                val currentDate: Date = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes: Long = currentDate!!.time / 60000

                // Calculate the age in minutes
                val ageInMinutes: Long = currentDateInMinutes - selectedDateInMinutes

                // Display the calculated age in minutes in the TextView
                val tvCalculatedAgeInMinutes: TextView = findViewById(R.id.tvCalculatedAgeInMinutes)
                tvCalculatedAgeInMinutes.text = "$ageInMinutes"

                // Calculate the age in hours
                val ageInHours: Long = ageInMinutes / 60
                val tvCalculatedAgeInHours: TextView = findViewById(R.id.tvCalculatedAgeInHours)
                tvCalculatedAgeInHours.text = "$ageInHours"

                // Calculate the age in days
                val ageInDays: Long = ageInHours / 24
                val tvCalculatedAgeInDays: TextView = findViewById(R.id.tvCalculatedAgeInDays)
                tvCalculatedAgeInDays.text = "$ageInDays"

                // Calculate the age in weeks
                val ageInWeeks: Long = ageInDays / 7
                val tvCalculatedAgeInWeeks: TextView = findViewById(R.id.tvCalculatedAgeInWeeks)
                tvCalculatedAgeInWeeks.text = "$ageInWeeks"

                // Calculate the age in months
                val ageInMonths: Long = ageInDays / 30
                val tvCalculatedAgeInMonths: TextView = findViewById(R.id.tvCalculatedAgeInMonths)
                tvCalculatedAgeInMonths.text = "$ageInMonths"

            },
            currentYear,
            currentMonth,
            currentDay
        )

        // Limit the calendar to prevent selecting future values
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()

    }
}
