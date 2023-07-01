package com.dicoding.todoapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.ui.list.TaskActivity
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[DetailTaskViewModel::class.java]
        val taskId = intent.getIntExtra(TASK_ID, 0)
        viewModel.setTaskId(taskId)
        val edTitle = findViewById<TextInputEditText>(R.id.detail_ed_title)
        val edDesc = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val edDueDate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)
        val button = findViewById<Button>(R.id.btn_delete_task)
        viewModel.task.observe(this) {task ->
            edTitle.apply {
                if (text.isNullOrEmpty()) {
                    text?.append(task.title)
                }
            }
            edDesc.apply {
                if (text.isNullOrEmpty()) {
                    text?.append(task.description)
                }
            }
            edDueDate.apply {
                if (text.isNullOrEmpty()) {
                    text?.append(DateConverter.convertMillisToString(task.dueDateMillis))
                }
            }
        }
        button.setOnClickListener {
            viewModel.deleteTask()
            val intent = Intent(this, TaskActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

    }
}