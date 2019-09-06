package jp.co.cyberagent.dojo2019

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.db.User
import jp.co.cyberagent.dojo2019.db.UserDao

class UserRepository (private val userDao: UserDao){

    val allUser: LiveData<List<User>> = userDao.getAll()

    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    @WorkerThread
    suspend fun getAll(){
        userDao.getAll()
    }


}