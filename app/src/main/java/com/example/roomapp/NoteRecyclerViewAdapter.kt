package com.example.roomapp

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item_row.view.*

class NoteRecyclerViewAdapter(private var notes: List<RoomNote>, val ctx: MainActivity): RecyclerView.Adapter<NoteRecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.note_item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val note = notes[position]

        holder.itemView.apply {
            tv_note.text = note.note
            iv_delete.setOnClickListener {
                val builder = AlertDialog.Builder(holder.itemView.context)
                builder.setTitle("Are you sure you want to delete this note? ")
                builder.setNegativeButton("Cancel"){_, _ -> }
                builder.setPositiveButton("Yes"){_, _ -> ctx.delete(note.pk)}
                builder.show()
            }

            iv_edit.setOnClickListener {
                val builder = AlertDialog.Builder(holder.itemView.context)
                builder.setTitle("Update note")
                val ed = EditText(holder.itemView.context)
                ed.hint = note.note
                builder.setView(ed)
                builder.setNegativeButton("Cancel"){_, _ -> }
                builder.setPositiveButton("Yes"){_, _ ->
                    if(ed.text.isNotEmpty())
                        ctx.update(note.pk, ed.text.toString())
                }
                builder.show()
            }
        }
    }

    override fun getItemCount(): Int = notes.size


    fun updateRecyclerView(notes:MutableList<RoomNote>){
        this.notes = notes
        notifyDataSetChanged()
    }
}