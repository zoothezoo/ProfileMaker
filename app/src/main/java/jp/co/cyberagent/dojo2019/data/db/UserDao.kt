package jp.co.cyberagent.dojo2019.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

//SQL文をいい感じに使える
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    //@Query("SELECT * FROM user WHERE uid IN (:userIds)")
    //fun loadAllByIds(userIds: IntArray): List<User>

    //@Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
    //        "last_name LIKE :last LIMIT 1")
    //fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Delete
    fun delete(user: User)




}