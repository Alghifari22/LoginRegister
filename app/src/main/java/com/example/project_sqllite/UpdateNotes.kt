package com.example.project_sqllite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_sqllite.databinding.ActivityUpdateNotesBinding

class UpdateNotes : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateNotesBinding
    private lateinit var db : notesdatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = notesdatabaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if(noteId == -1){
            finish()
            return
        }

        val note = db.getNotebyID(noteId)
        binding.etTitleEdit.setText(note.title)
        binding.etContentEdit.setText(note.content)

        binding.btnSaveEdit.setOnClickListener{
            val newTitle = binding.etTitleEdit.text.toString()
            val newContent = binding.etContentEdit.text.toString()
            val update = Note(noteId,newTitle,newContent)
            db.updatenotes(update)
            finish()
            Toast.makeText(this, "Notes Succesfully updated",Toast.LENGTH_SHORT).show()
        }
    }
}
