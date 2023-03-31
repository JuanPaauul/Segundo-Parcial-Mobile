import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IBookDao {

    @Query("SELECT * FROM book_table2")
    fun getList(): List<BookTable>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book:BookTable)

    @Query("DELETE FROM book_table2")
    suspend fun deleteAll()
}
