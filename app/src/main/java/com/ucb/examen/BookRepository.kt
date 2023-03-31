class BookRepository(private val bookDao: IBookDao) {

    suspend fun insert(book: BookTable) {
        bookDao.insert(book)
    }

    fun getListBooks(): List<BookTable> {
        return bookDao.getList()
    }
}
