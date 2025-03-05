package com.example.project_sqllite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_sqllite.databinding.ActivityAddNotesBinding

class AddNotes : AppCompatActivity() {
    private lateinit var binding : ActivityAddNotesBinding
    private lateinit var dbHelper: notesdatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = notesdatabaseHelper(this)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()
            val note = Note(0, title, content)
            dbHelper.insertNote(note)
            finish()
            Toast.makeText(this,"Succesfully added notes",Toast.LENGTH_SHORT).show()
        }
    }

}
