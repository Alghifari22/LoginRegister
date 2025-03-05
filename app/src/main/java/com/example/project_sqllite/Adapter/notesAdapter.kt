package com.example.project_sqllite.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.project_sqllite.Note
import com.example.project_sqllite.R
import com.example.project_sqllite.UpdateNotes
import com.example.project_sqllite.notesdatabaseHelper

class notesAdapter(private var notes : List<Note>, context: Context) : RecyclerView.Adapter<notesAdapter.NoteViewHolder>(){
    private val db : notesdatabaseHelper  = notesdatabaseHelper(context)

    class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        val tvContent : TextView = itemView.findViewById(R.id.tvContent)
        val updateBtn : ImageView = itemView.findViewById(R.id.ivEdit)
        val deleteBtn : ImageView = itemView.findViewById(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.tvTitle.text = note.title
        holder.tvContent.text = note.content

        holder.updateBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateNotes::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteBtn.setOnClickListener {
            val context = holder.itemView.context
            AlertDialog.Builder(context)
                .setTitle("Konfirmasi Hapus")
                .setMessage("Apakah Anda yakin ingin menghapus note ini?")
                .setPositiveButton("Ya") { _, _ ->
                    db.deleteNote(note.id)
                    refreshdata(db.getAllNotes())
                    Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    fun refreshdata(newNotes: List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }

}
