package com.dicoding.courseschedule.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.home.ViewModelFactory
import com.dicoding.courseschedule.util.TimePickerFragment
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    private lateinit var viewModel: AddCourseViewModel
    private lateinit var edCourseName : TextInputEditText
    private lateinit var edNote : TextInputEditText
    private lateinit var edLecturer : TextInputEditText
    private lateinit var tvStart : TextView
    private lateinit var tvEnd : TextView
    private lateinit var ibStart : ImageButton
    private lateinit var ibEnd : ImageButton
    private lateinit var dayOW : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.add_course)
        }

        val factory = ViewModelFactory.createFactory(this)
        viewModel = ViewModelProvider(this, factory)[AddCourseViewModel::class.java]

        initComponents()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_insert) {

            val name = edCourseName.text.toString()
            val lecturer = edLecturer.text.toString()
            val note = edNote.text.toString()
            val start = tvStart.text.toString()
            val end = tvEnd.text.toString()
            val day = dayOW.selectedItemPosition
            if (!(name.isEmpty()||lecturer.isEmpty()||note.isEmpty()||start.isEmpty()||end.isEmpty())) {
                viewModel.insertCourse(
                    courseName = name,
                    lecturer = lecturer,
                    note = note,
                    day = day,
                    startTime = start,
                    endTime = end,
                )
                finish()
            } else {
                Toast.makeText(this, "Some Fields Are Empty!", Toast.LENGTH_SHORT).show()
            }
            true
        }
        else super.onOptionsItemSelected(item)
    }

    private fun initComponents() {
        edCourseName = findViewById(R.id.ed_course_name)
        edLecturer = findViewById(R.id.ed_lecturer)
        edNote = findViewById(R.id.ed_note)
        tvStart = findViewById(R.id.tv_start_time)
        tvEnd = findViewById(R.id.tv_end_time)
        ibStart = findViewById(R.id.ib_start_time)
        ibEnd = findViewById(R.id.ib_end_time)
        dayOW = findViewById(R.id.spinner_day)

        ibStart.setOnClickListener {
            showTimeDialog(START)
        }
        ibEnd.setOnClickListener {
            showTimeDialog(END)
        }
    }

    private fun showTimeDialog(tag : String) {
        val dialogTime = TimePickerFragment()
        dialogTime.show(supportFragmentManager, tag)
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)
        val hf = SimpleDateFormat("HH:mm", Locale.getDefault()).format(cal.time)
        when (tag) {
            START -> {
                tvStart.text = hf
            }
            END -> {
                tvEnd.text = hf
            }
        }
    }

    companion object {
        const val START = "START"
        const val END = "END"
    }

}