package jp.co.cyberagent.dojo2019.data.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import jp.co.cyberagent.dojo2019.data.db.AppDatabase
import jp.co.cyberagent.dojo2019.data.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel (
    application: Application
): AndroidViewModel(application){

    private val repository: UserRepository
    val allUser: LiveData<List<User>>

    init {
        val userDao = AppDatabase.getInstance().userDao()
        repository = UserRepository(userDao)
        allUser= repository.allUser
    }

    //本当はレポジトリで非同期
    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO){
        async {
            repository.insert(user)
        }.await()
    }

    fun getAll() = viewModelScope.launch(Dispatchers.Main){
        async(context = Dispatchers.IO) {
            repository.getAll()
        }.await()
    }
}