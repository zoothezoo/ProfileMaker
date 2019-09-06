package jp.co.cyberagent.dojo2019.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.data.db.User
import jp.co.cyberagent.dojo2019.data.db.UserDao

class UserRepository (private val userDao: UserDao){

    val allUser: LiveData<List<User>> = userDao.getAll()

    @WorkerThread
    fun insert(user: User) {
        userDao.insert(user)
    }
    @WorkerThread
    fun getAll(){
        userDao.getAll()
    }
}