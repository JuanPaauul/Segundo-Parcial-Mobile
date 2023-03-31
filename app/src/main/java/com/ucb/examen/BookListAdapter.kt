package com.ucb.examen

import BookTable
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookListAdapter(val items: List<BookTable>, val context: Context): RecyclerView.Adapter<BookListAdapter.BookListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.book_row, parent, false)
        return BookListViewHolder(v)
    }
    override fun getItemCount(): Int {
        return items.count()
    }
    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.findViewById<TextView>(R.id.tv_title).text = item.title
        holder.itemView.findViewById<TextView>(R.id.tv_description).text = item.description
    }
    class BookListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}
