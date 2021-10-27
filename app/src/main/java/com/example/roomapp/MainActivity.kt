package com.example.roomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var notes: MutableList<RoomNote>
    private lateinit var adapter: NoteRecyclerViewAdapter
    private lateinit var rvMain: RecyclerView

    private lateinit var etAdd: EditText
    private lateinit var btnAdd: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        NoteDatabase.getInstance(applicationContext)

        notes = mutableListOf()

        fetchData()

        rvMain = findViewById(R.id.rvMain)
        etAdd = findViewById(R.id.et_add_note)
        btnAdd = findViewById(R.id.btn_add_note)
        adapter = NoteRecyclerViewAdapter(notes, this)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            if(etAdd.text.isNotEmpty()){
                NoteDatabase.getInstance(applicationContext).StudentDao().addNote(RoomNote(0, etAdd.text.toString()))
                Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show()
                adapter.updateRecyclerView(NoteDatabase.getInstance(applicationContext).StudentDao().getAllNotes())
            }
        }
    }

    private fun fetchData(){
        CoroutineScope(IO).launch {
            notes = NoteDatabase.getInstance(applicationContext).StudentDao().getAllNotes()
            adapter = NoteRecyclerViewAdapter(notes, this@MainActivity)
            rvMain.adapter = adapter
        }
    }

    fun delete(pk: Int){
        NoteDatabase.getInstance(applicationContext).StudentDao().deleteNote(pk)
        adapter.updateRecyclerView(NoteDatabase.getInstance(applicationContext).StudentDao().getAllNotes())
    }

    fun update(pk: Int, newNote: String){
        NoteDatabase.getInstance(applicationContext).StudentDao().updateNote(pk, newNote)
        adapter.updateRecyclerView(NoteDatabase.getInstance(applicationContext).StudentDao().getAllNotes())

    }
}