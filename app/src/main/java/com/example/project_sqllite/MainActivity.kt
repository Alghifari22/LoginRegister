package com.example.project_sqllite

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_sqllite.Adapter.notesAdapter
import com.example.project_sqllite.databinding.ActivityMainBinding
import com.example.project_sqllite.databinding.ActivityRegisterBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var db : notesdatabaseHelper
    private lateinit var notesAdapter : notesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = notesdatabaseHelper(this)
        notesAdapter = notesAdapter(db.getAllNotes(),this)

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = notesAdapter

        binding.btnAddNotes.setOnClickListener {
            startActivity(Intent(this, AddNotes::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshdata(db.getAllNotes())
    }
}
