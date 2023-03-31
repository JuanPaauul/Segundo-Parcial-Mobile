package com.ucb.examen

import BookRepository
import BookTable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        val bookList = arrayListOf<Book>()
        val restApiAdapter = RestApiAdapter()
        val endPoint = restApiAdapter.connectionApi()
        val bookResponseCall = endPoint.getAllPost()
        bookResponseCall.enqueue( object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val posts = response?.body()
                Log.d("RESP POST", Gson().toJson(posts))
                posts?.forEach {
                    bookList.add(Book(it.userId, it.id, it.title, it.body))
                }

                recyclerView = findViewById(R.id.recycler_view)
                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = BookListAdapter(bookList.map { BookTable(it.title, it.description) }, this@RecyclerViewActivity)
                /*GlobalScope.launch {
                    val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
                    val repository = BookRepository(bookDao)
                    posts?.forEach {
                        repository.insert(BookTable(it.title, it.body))
                    }
                    recyclerView.adapter = BookListAdapter(repository.getListBooks(), this@RecyclerViewActivity)
                }*/
                Log.d("RESP POST", "AUN NO TERMINOOOOOOOOOOOO")
            }
        })
        Log.d("RESP POST", "TERMINOOOOOOOOOOOO")
    }
}