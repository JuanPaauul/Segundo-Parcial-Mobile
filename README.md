# Segundo parcial de programacion de dispositivos moviles

Consumir el siguiente metodo, https:/Ijsonplaceholder.typicode.com/posts 20 puntos ✔️ <br>
Implementar el RecyclerView que muestre la lista de POST, con los campos (title, body) 25 puntos ✔️<br>
Crear la base de datos 15 puntos ✔️<br>
Crear una Actividad con los siguientes campos (title, description) 15 puntos ✔️<br>
Guardar en la tabla book, con los campos( title, description) obtenidos de la Actividad anterior. 25 puntos

## Problems
When trying to initialize the AppRoomDatabase, I get and strange error which is:
```
2023-03-30 23:39:06.493 3036-3036/com.ucb.examen E/RecyclerView: No adapter attached; skipping layout
2023-03-30 23:39:06.750 3036-3177/com.ucb.examen E/AndroidRuntime: FATAL EXCEPTION: DefaultDispatcher-worker-2
    Process: com.ucb.examen, PID: 3036
    java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String java.lang.Package.getName()' on a null object reference
        at androidx.room.Room.getGeneratedImplementation(Room.java:82)
        at androidx.room.RoomDatabase$Builder.build(RoomDatabase.java:1486)
        at AppRoomDatabase$Companion.getDatabase(AppRoomDatabase.kt:19)
        at com.ucb.examen.RecyclerViewActivity$onCreate$1$onResponse$2.invokeSuspend(RecyclerViewActivity.kt:46)
        at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
        at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:106)
        at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:570)
        at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:750)
        at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:677)
        at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:664)
    	Suppressed: kotlinx.coroutines.DiagnosticCoroutineContextException: [StandaloneCoroutine{Cancelling}@c51fd75, Dispatchers.Default]
```
And this is the follow code where this issue happends:
```
override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
    val posts = response?.body()
    Log.d("RESP POST", Gson().toJson(posts))
    posts?.forEach {
        bookList.add(Book(it.userId, it.id, it.title, it.body))
    }

    recyclerView = findViewById(R.id.recycler_view)
    recyclerView.layoutManager = linearLayoutManager

    GlobalScope.launch {
        val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
        val repository = BookRepository(bookDao)
        posts?.forEach {
            repository.insert(BookTable(it.title, it.body))
        }
        recyclerView.adapter = BookListAdapter(repository.getListBooks(), this@RecyclerViewActivity)
    }
```
If I solve this error, I will upload it.
